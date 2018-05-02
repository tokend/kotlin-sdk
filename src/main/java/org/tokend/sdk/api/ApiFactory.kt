package org.tokend.sdk.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonParser
import okhttp3.*
import okhttp3.internal.Util
import okhttp3.logging.HttpLoggingInterceptor
import org.tokend.sdk.api.models.SocialLinks
import org.tokend.sdk.api.requests.CookieJarProvider
import org.tokend.sdk.api.requests.RequestSigner
import org.tokend.sdk.api.responses.ServerError
import org.tokend.sdk.federation.NeedTfaException
import org.tokend.sdk.utils.ApiDateUtil
import org.tokend.sdk.utils.extentions.hash
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.nio.charset.Charset
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext

object ApiFactory {
    private val REQUEST_TIMEOUT = 20 * 1000
    private val SIGNATURE_VALID_SEC = 60

    private var apiService: ApiService? = null
    private var mApiUrl: String? = null
    private var accountId: String? = null
    private var signedApiService: ApiService? = null
    private var requestSigner: RequestSigner? = null
    private var cookieJarProvider: CookieJarProvider? = null

    @JvmStatic
    @JvmOverloads
    fun getApiService(url: String, cookieJar: CookieJarProvider? = null): ApiService {
        if (apiService == null || mApiUrl != url || cookieJarProvider != cookieJar) {
            val retrofit = createBaseRetrofitConfig(url).build()
            apiService = retrofit.create(ApiService::class.java)
        }
        return apiService!!
    }

    @JvmStatic
    @JvmOverloads
    fun getApiService(apiUrl: String, accountId: String, signer: RequestSigner, cookieJar: CookieJarProvider? = null)
            : ApiService {
        if (signedApiService == null || apiUrl != mApiUrl || signer != requestSigner || this.accountId != accountId) {
            this.cookieJarProvider = cookieJar
            this.accountId = accountId
            this.requestSigner = signer
            val retrofit = createBaseRetrofitConfig(apiUrl = apiUrl, signed = true).build()
            signedApiService = retrofit.create(ApiService::class.java)
        }
        return signedApiService!!
    }

    @JvmStatic
    fun getBaseGson(): Gson {
        val builder = GsonBuilder()
                .serializeNulls()
                .registerTypeAdapter(SocialLinks::class.java, SocialLinks.SocialLinksDeserializer())
                .registerTypeAdapter(Date::class.java, getGsonDateDeserializer())
        return builder.create()
    }

    @JvmStatic
    @JvmOverloads
    fun getBaseHttpClient(isSigned: Boolean = false, cookieJar: CookieJarProvider? = null): OkHttpClient {
        val sslContext = SSLContext.getInstance("TLSv1.2")
        sslContext.init(null, null, null)
        val sslFactory = sslContext.socketFactory

        val clientBuilder = OkHttpClient.Builder()
                .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                .sslSocketFactory(sslFactory)

        val connectionSpec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .cipherSuites(
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
                .build()

        clientBuilder.apply {
            connectionSpecs(Arrays.asList(connectionSpec, ConnectionSpec.CLEARTEXT))
            addInterceptor(createHandleTfaInterceptor())
            val jar = cookieJar?.getCookieJar()
            if (jar != null)
                cookieJar(jar)
            if (isSigned) {
                addInterceptor(createSignInterceptor())
            }
            addInterceptor(createLoggingInterceptor())
        }

        return clientBuilder.build()
    }

    @JvmStatic
    private fun createBaseRetrofitConfig(apiUrl: String, signed: Boolean = false, cookieJar: CookieJarProvider? = null)
            : Retrofit.Builder {
        val client = getBaseHttpClient(signed, cookieJar)

        return Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(createBaseJsonConverterFactory())
                .baseUrl(apiUrl)
                .client(client)
    }

    @JvmStatic
    internal fun createBaseJsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(getBaseGson())
    }

    @JvmStatic
    private fun createLoggingInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @JvmStatic
    private fun createHandleTfaInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)

            if (response?.code() == 403) {
                val responseBuffer = response.body().source().buffer().clone()
                var responseString = "{}"

                try {
                    val responseCharset =
                            Util.bomAwareCharset(responseBuffer, Charset.defaultCharset())
                    responseString = responseBuffer.readString(responseCharset)
                } finally {
                    Util.closeQuietly(responseBuffer)
                }

                val responseJson = JsonParser().parse(responseString).asJsonObject
                val errors = responseJson["errors"]?.asJsonArray

                if (errors?.size() ?: 0 > 0) {
                    val error = getBaseGson().fromJson(errors?.get(0), ServerError::class.java)
                    if (error.detail?.contains("factor") == true) {
                        throw NeedTfaException(
                                (error.meta?.get(NeedTfaException.BACKEND_ID) as? Double)?.toInt()
                                        ?: 0,
                                (error.meta?.get(NeedTfaException.BACKEND_TYPE) as? String).toString(),
                                (error.meta?.get(NeedTfaException.TOKEN) as? String).toString(),
                                (error.meta?.get(NeedTfaException.KEYCHAIN_DATA) as? String).toString(),
                                (error.meta?.get(NeedTfaException.SALT) as? String).toString(),
                                (error.meta?.get(NeedTfaException.WALLET_ID) as? String).toString())
                    }
                }
            }
            response!!
        }
    }

    @JvmStatic
    private fun createSignInterceptor(): Interceptor {
        return Interceptor { chain ->
            val newRequest = buildSignedRequest(chain)
            chain.proceed(newRequest)
        }
    }

    @JvmStatic
    private fun getGsonDateDeserializer(): JsonDeserializer<Date?> {
        return JsonDeserializer<Date?> { json, _, _ ->
            ApiDateUtil.tryParseDate(json?.asString)
        }
    }

    @JvmStatic
    private fun buildSignedRequest(chain: Interceptor.Chain): Request {
        val validUntil = java.lang.Double.valueOf(Math.floor((Date().time / 1000 +
                SIGNATURE_VALID_SEC).toDouble()))!!.toInt().toString()
        val url = chain.request().url().url()
        val fullUrlPath = if (!url.query.isNullOrEmpty()) {
            "${url.path}?${url.query}"
        } else {
            url.path
        }
        val signatureBase = "{ uri: '$fullUrlPath', valid_untill: '$validUntil'}"
        val data = signatureBase.toByteArray().hash()
        val signedData = String(requestSigner?.sing(data) ?: byteArrayOf())

        return chain.request().newBuilder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("X-AuthValidUnTillTimestamp", validUntil)
                .addHeader("X-AuthPublicKey", accountId)
                .addHeader("X-AuthSignature", signedData)
                .build()
    }
}