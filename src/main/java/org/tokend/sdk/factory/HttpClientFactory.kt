package org.tokend.sdk.factory

import okhttp3.ConnectionSpec
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.tokend.sdk.utils.CookieJarProvider
import org.tokend.sdk.utils.CustomHeadersInterceptor
import java.security.KeyStore
import java.security.SecureRandom
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

/**
 * Constructs base http client builder.
 */
class HttpClientFactory {
    @JvmOverloads
    fun getBaseHttpClientBuilder(cookieJarProvider: CookieJarProvider? = null,
                                 requestTimeoutMs: Long = REQUEST_TIMEOUT,
                                 headers: Map<String, String?>? = null,
                                 withLogs: Boolean = false)
            : OkHttpClient.Builder {
        val sslContext = SSLContext.getInstance("TLSv1.2")
        val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        val defaultKeystore: KeyStore? = null
        trustManagerFactory.init(defaultKeystore)
        val trustManagers = trustManagerFactory.trustManagers
        val x509trustManager = trustManagers.firstOrNull() as? X509TrustManager
                ?: throw IllegalStateException("No X509TrustManager found")
        sslContext.init(null, trustManagers, SecureRandom())
        val sslFactory = sslContext.socketFactory

        val clientBuilder = OkHttpClient.Builder()
                .readTimeout(requestTimeoutMs, TimeUnit.MILLISECONDS)
                .connectTimeout(requestTimeoutMs, TimeUnit.MILLISECONDS)
                .sslSocketFactory(sslFactory, x509trustManager)

        val connectionSpec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .allEnabledTlsVersions()
                .allEnabledCipherSuites()
                .build()

        clientBuilder.apply {
            connectionSpecs(Arrays.asList(connectionSpec, ConnectionSpec.CLEARTEXT))
            cookieJarProvider?.getCookieJar()?.also { cookieJar(it) }

            if (headers != null) {
                addInterceptor(CustomHeadersInterceptor(headers))
            }

            if (withLogs) {
                addInterceptor(getLoggingInterceptor())
            }
        }

        return clientBuilder
    }

    private fun getLoggingInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    companion object {
        const val REQUEST_TIMEOUT = 30 * 1000L
    }
}