package org.tokend.sdk.factory

import okhttp3.OkHttpClient
import org.tokend.sdk.signing.RequestSigner
import org.tokend.sdk.signing.SignInterceptor
import org.tokend.sdk.tfa.TfaCallback
import org.tokend.sdk.tfa.TfaInterceptor
import org.tokend.sdk.tfa.TfaVerificationService
import org.tokend.sdk.utils.CookieJarProvider
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.Executor

/**
 * Constructs API services from Retrofit interfaces.
 */
class ServiceFactory(private val url: String,
                     private val withLogs: Boolean,
                     private val asyncCallbackExecutor: Executor,
                     private val extraHeaders: Map<String, String?>? = null

) {
    fun getTfaVerificationService(): TfaVerificationService {
        return getCustomService(TfaVerificationService::class.java,
                HttpClientFactory().getBaseHttpClientBuilder(
                        headers = getDefaultHeaders(extraHeaders),
                        withLogs = withLogs

                ).build())
    }

    /**
     * Constructs API service from given Retrofit interface.
     * @param requestSigner required to perform requests
     * with signature check
     * @param tfaCallback required to handle TFA requests
     * @param cookieJarProvider persistent cookie storage
     */
    @JvmOverloads
    fun <T> getCustomService(serviceClass: Class<T>,
                             requestSigner: RequestSigner? = null,
                             tfaCallback: TfaCallback? = null,
                             cookieJarProvider: CookieJarProvider? = null): T {
        val client =
                HttpClientFactory().getBaseHttpClientBuilder(
                        cookieJarProvider = cookieJarProvider,
                        headers = getDefaultHeaders(extraHeaders),
                        withLogs = withLogs
                )
                        .addInterceptor(
                                TfaInterceptor(getTfaVerificationService(),
                                        tfaCallback)
                        )
                        .apply {
                            if (requestSigner != null) {
                                interceptors().add(
                                        0,
                                        SignInterceptor(
                                                url,
                                                requestSigner
                                        )
                                )
                            }
                        }
                        .build()

        val retrofit = getBaseRetrofitConfig(client).build()

        return retrofit.create(serviceClass)
    }

    /**
     * Constructs API service from given Retrofit interface
     * using given HTTP client.
     */
    fun <T> getCustomService(serviceClass: Class<T>, httpClient: OkHttpClient): T {
        return getBaseRetrofitConfig(httpClient)
                .build()
                .create(serviceClass)
    }

    private fun getBaseRetrofitConfig(httpClient: OkHttpClient)
            : Retrofit.Builder {
        return Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JsonApiTools.getJsonApiConverterFactory())
                .addConverterFactory(JsonApiTools.getJacksonConverterFactory())
                .baseUrl(url)
                .client(httpClient)
                .callbackExecutor(asyncCallbackExecutor)
    }

    private fun getDefaultHeaders(extraHeaders: Map<String, String?>?): Map<String, String?> {
        return mutableMapOf(
                HEADER_CONTENT_TYPE_NAME to CONTENT_TYPE,
                HEADER_ACCEPT_NAME to CONTENT_TYPE
        ).apply {
            extraHeaders?.let {
                putAll(filterNotNullValues(it))
            }
        }
    }

    private fun filterNotNullValues(map: Map<String, String?>): Map<String, String> {
        return map.filterValues {
            it != null
        }.mapValues { it -> it.value as String }
    }

    companion object {
        private const val HEADER_ACCEPT_NAME = "Accept"
        private const val HEADER_CONTENT_TYPE_NAME = "Content-Type"
        private const val CONTENT_TYPE = "application/vnd.api+json"
    }
}