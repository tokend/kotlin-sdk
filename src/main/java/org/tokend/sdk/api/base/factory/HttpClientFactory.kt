package org.tokend.sdk.api.base.factory

import okhttp3.ConnectionSpec
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import org.tokend.sdk.api.UserAgentInterceptor
import org.tokend.sdk.api.requests.CookieJarProvider
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext

class HttpClientFactory {
    @JvmOverloads
    fun getBaseHttpClientBuilder(cookieJarProvider: CookieJarProvider? = null,
                                 requestTimeoutMs: Long = REQUEST_TIMEOUT,
                                 userAgent: String? = null)
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

            if (userAgent != null) {
                addInterceptor(UserAgentInterceptor(userAgent))
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