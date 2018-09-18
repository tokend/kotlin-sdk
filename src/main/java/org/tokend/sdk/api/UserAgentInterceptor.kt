package org.tokend.sdk.api

import okhttp3.Interceptor
import okhttp3.Response

class UserAgentInterceptor(
        private val userAgent: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response? {
        val request =
                chain.request().newBuilder()
                        .addHeader(USER_AGENT_HEADER, userAgent)
                        .build()
        return chain.proceed(request)
    }

    companion object {
        const val USER_AGENT_HEADER = "User-Agent"
    }
}