package org.tokend.sdk.keyserver

import okhttp3.OkHttpClient
import org.tokend.sdk.api.ApiFactory
import org.tokend.sdk.api.SignInterceptor
import org.tokend.sdk.api.requests.CookieJarProvider
import org.tokend.sdk.api.requests.RequestSigner
import org.tokend.sdk.api.tfa.TfaCallback
import org.tokend.sdk.api.tfa.TfaOkHttpInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object KeyServerApiFactory {
    private var keyServerApi: KeyServerApi? = null
    private var accountId: String? = null
    private var signedKeyServerApi: KeyServerApi? = null
    private var requestSigner: RequestSigner? = null
    private var keyServerApiUrl: String? = null
    private var cookieJarProvider: CookieJarProvider? = null

    @JvmStatic
    @JvmOverloads
    fun getApiService(keyServerApiUrl: String, tfaCallback: TfaCallback? = null,
                      cookieJar: CookieJarProvider? = null): KeyServerApi {
        if (keyServerApi == null || this.keyServerApiUrl != keyServerApiUrl || cookieJarProvider != cookieJar) {
            this.keyServerApiUrl = keyServerApiUrl
            this.cookieJarProvider = cookieJar

            val tfaVerificationService = ApiFactory.getTfaVerificationService(keyServerApiUrl)
            val tfaInterceptor = TfaOkHttpInterceptor(tfaVerificationService, tfaCallback)
            val client = ApiFactory.getBaseHttpClientBuilder(cookieJar)
                    .addInterceptor(tfaInterceptor)
                    .build()
            val retrofit = createBaseRetrofitConfig(keyServerApiUrl, client).build()

            keyServerApi = retrofit.create(KeyServerApi::class.java)
        }
        return keyServerApi!!
    }

    @JvmStatic
    @JvmOverloads
    fun getApiService(keyServerApiUrl: String, accountId: String, signer: RequestSigner,
                      tfaCallback: TfaCallback? = null, cookieJar: CookieJarProvider? = null)
            : KeyServerApi {
        if (signedKeyServerApi == null || this.keyServerApiUrl != keyServerApiUrl
                || signer != requestSigner || this.accountId != accountId) {
            this.cookieJarProvider = cookieJar
            this.accountId = accountId
            this.requestSigner = signer

            val tfaVerificationService = ApiFactory.getTfaVerificationService(keyServerApiUrl)
            val tfaInterceptor = TfaOkHttpInterceptor(tfaVerificationService, tfaCallback)
            val client = ApiFactory.getBaseHttpClientBuilder(cookieJar)
                    .addInterceptor(tfaInterceptor)
                    .addInterceptor(SignInterceptor(accountId, signer, ApiFactory.SIGNATURE_VALID_SEC))
                    .build()
            val retrofit = createBaseRetrofitConfig(keyServerApiUrl, client).build()

            signedKeyServerApi = retrofit.create(KeyServerApi::class.java)
        }
        return signedKeyServerApi!!
    }

    @JvmStatic
    private fun createBaseRetrofitConfig(apiUrl: String, httpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(ApiFactory.createBaseJsonConverterFactory())
                .baseUrl(apiUrl)
                .client(httpClient)
    }
}