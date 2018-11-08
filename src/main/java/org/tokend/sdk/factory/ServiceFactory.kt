package org.tokend.sdk.factory

import okhttp3.OkHttpClient
import org.tokend.sdk.api.general.GeneralService
import org.tokend.sdk.signing.RequestSigner
import org.tokend.sdk.signing.SignInterceptor
import org.tokend.sdk.tfa.TfaCallback
import org.tokend.sdk.tfa.TfaInterceptor
import org.tokend.sdk.tfa.TfaVerificationService
import org.tokend.sdk.utils.CookieJarProvider
import org.tokend.sdk.utils.TimeCorrectionProvider
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*

/**
 * Constructs API services from Retrofit interfaces.
 */
class ServiceFactory(private val url: String,
                     private val userAgent: String? = null,
                     private val forceContentType: Boolean = false,
                     private val withLogs: Boolean) {
    fun getTfaVerificationService(): TfaVerificationService {
        return getCustomService(TfaVerificationService::class.java,
                HttpClientFactory().getBaseHttpClientBuilder(
                        headers = getDefaultHeaders(userAgent),
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
                        headers = getDefaultHeaders(userAgent),
                        withLogs = withLogs
                )
                        .addInterceptor(
                                TfaInterceptor(getTfaVerificationService(),
                                        tfaCallback)
                        )
                        .apply {
                            if (requestSigner != null) {
                                addInterceptor(
                                        SignInterceptor(
                                                url,
                                                requestSigner,
                                                getTimeCorrectionProvider(),
                                                SIGNATURE_VALID_SEC
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
                .addConverterFactory(GsonFactory().getBaseGsonConverterFactory())
                .baseUrl(url)
                .client(httpClient)
    }

    private fun getTimeCorrectionProvider(): TimeCorrectionProvider {
        return object : TimeCorrectionProvider {
            override fun getTimeCorrection(): Long {
                return timeCorrections.getOrPut(url) {
                    return@getOrPut try {
                        val currentTime = Date().time / 1000
                        val serverTime = getCustomService(GeneralService::class.java)
                                .getSystemInfo()
                                .execute()
                                .body()
                                .currentTime

                        serverTime - currentTime
                    } catch (e: Exception) {
                        e.printStackTrace()
                        0L
                    }
                }
            }
        }
    }

    private fun getDefaultHeaders(userAgent: String?): Map<String, String?> {
        if (forceContentType) {
            return mapOf(
                    HEADER_USER_AGENT_NAME to userAgent,
                    HEADER_CONTENT_TYPE_NAME to CONTENT_TYPE,
                    HEADER_ACCEPT_NAME to CONTENT_TYPE
            )
        } else {
            return mapOf(
                    HEADER_USER_AGENT_NAME to userAgent
            )
        }
    }

    companion object {
        // Time corrections by URL.
        private var timeCorrections = mutableMapOf<String, Long>()

        const val SIGNATURE_VALID_SEC = 60

        private const val HEADER_USER_AGENT_NAME = "User-Agent"
        private const val HEADER_ACCEPT_NAME = "Accept"
        private const val HEADER_CONTENT_TYPE_NAME = "Content-Type"
        private const val CONTENT_TYPE = "application/vnd.api+json"
    }
}