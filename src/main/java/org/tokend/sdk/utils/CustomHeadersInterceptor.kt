package org.tokend.sdk.utils

import okhttp3.Interceptor
import okhttp3.Response

class CustomHeadersInterceptor(
        private val headers: Map<String, String?>
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        headers.forEach {
            val value = it.value
            if (value != null) {
                requestBuilder.addHeader(it.key, value)
            }
        }

        return chain.proceed(requestBuilder.build())
    }
}