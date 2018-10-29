package org.tokend.sdk.factory

import okhttp3.ConnectionSpec
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import org.tokend.sdk.utils.CookieJarProvider
import org.tokend.sdk.utils.CustomHeadersInterceptor
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext

/**
 * Constructs base http client builder.
 */
class HttpClientFactory {
    @JvmOverloads
    fun getBaseHttpClientBuilder(cookieJarProvider: CookieJarProvider? = null,
                                 requestTimeoutMs: Long = REQUEST_TIMEOUT,
                                 headers: Map<String, String?>? = null)
            : OkHttpClient.Builder {
        val sslContext = SSLContext.getInstance("TLSv1.2")
        sslContext.init(null, null, null)
        val sslFactory = sslContext.socketFactory

        val clientBuilder = OkHttpClient.Builder()
                .readTimeout(requestTimeoutMs, TimeUnit.MILLISECONDS)
                .connectTimeout(requestTimeoutMs, TimeUnit.MILLISECONDS)
                .sslSocketFactory(sslFactory)

        val connectionSpec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .allEnabledCipherSuites()
                .build()

        clientBuilder.apply {
            connectionSpecs(Arrays.asList(connectionSpec, ConnectionSpec.CLEARTEXT))
            cookieJarProvider?.getCookieJar()?.also { cookieJar(it) }

            if (headers != null) {
                addInterceptor(CustomHeadersInterceptor(headers))
            }

            addInterceptor(getLoggingInterceptor())
        }

        return clientBuilder
    }

    private fun getLoggingInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    companion object {
        const val REQUEST_TIMEOUT = 20 * 1000L
    }
}