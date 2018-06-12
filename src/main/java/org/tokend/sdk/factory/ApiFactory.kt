package org.tokend.sdk.factory

import okhttp3.OkHttpClient
import org.tokend.sdk.api.ApiService
import org.tokend.sdk.api.SignInterceptor
import org.tokend.sdk.api.requests.CookieJarProvider
import org.tokend.sdk.api.requests.RequestSigner
import org.tokend.sdk.api.tfa.TfaCallback
import org.tokend.sdk.api.tfa.TfaOkHttpInterceptor
import org.tokend.sdk.api.tfa.TfaVerificationService
import org.tokend.sdk.keyserver.KeyServerApi
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class ApiFactory(private val url: String) {
    @JvmOverloads
    fun getApiService(requestSigner: RequestSigner? = null,
                      tfaCallback: TfaCallback? = null,
                      cookieJarProvider: CookieJarProvider? = null): ApiService {
        return getCustomService(ApiService::class.java, requestSigner,
                tfaCallback, cookieJarProvider)
    }

    fun getTfaVerificationService(): TfaVerificationService {
        return getCustomService(TfaVerificationService::class.java,
                HttpClientFactory().getBaseHttpClientBuilder().build())
    }

    fun getKeyServerApi(requestSigner: RequestSigner? = null,
                        tfaCallback: TfaCallback? = null,
                        cookieJarProvider: CookieJarProvider? = null): KeyServerApi {
        return getCustomService(KeyServerApi::class.java, requestSigner,
                tfaCallback, cookieJarProvider)
    }

    @JvmOverloads
    fun <T> getCustomService(serviceClass: Class<T>,
                             requestSigner: RequestSigner? = null,
                             tfaCallback: TfaCallback? = null,
                             cookieJarProvider: CookieJarProvider? = null): T {
        val client =
                HttpClientFactory().getBaseHttpClientBuilder(cookieJarProvider)
                        .addInterceptor(
                                TfaOkHttpInterceptor(getTfaVerificationService(),
                                        tfaCallback)
                        )
                        .apply {
                            if (requestSigner != null) {
                                addInterceptor(SignInterceptor(requestSigner, SIGNATURE_VALID_SEC))
                            }
                        }
                        .build()

        val retrofit = getBaseRetrofitConfig(client).build()

        return retrofit.create(serviceClass)
    }

    fun <T> getCustomService(serviceClass: Class<T>, httpClient: OkHttpClient): T {
        return getBaseRetrofitConfig(httpClient)
                .build()
                .create(serviceClass)
    }

    private fun getBaseRetrofitConfig(httpClient: OkHttpClient)
            : Retrofit.Builder {
        return Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonFactory().getBaseGsonConverterFactory())
                .baseUrl(url)
                .client(httpClient)
    }

    companion object {
        const val SIGNATURE_VALID_SEC = 60
    }
}