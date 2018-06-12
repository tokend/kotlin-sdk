package org.tokend.sdk.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.tokend.sdk.api.requests.RequestSigner
import org.tokend.sdk.utils.extentions.hash
import java.util.*

class SignInterceptor(private val requestSigner: RequestSigner,
                      private val signatureValidSeconds: Int) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = buildSignedRequest(chain)
        return chain.proceed(newRequest)
    }

    private fun buildSignedRequest(chain: Interceptor.Chain): Request {
        val validUntil = java.lang.Double.valueOf(Math.floor((Date().time / 1000 +
                signatureValidSeconds).toDouble()))!!.toInt().toString()
        val url = chain.request().url().url()
        val fullUrlPath = if (!url.query.isNullOrEmpty()) {
            "${url.path}?${url.query}"
        } else {
            url.path
        }
        val signatureBase = "{ uri: '$fullUrlPath', valid_untill: '$validUntil'}"
        val data = signatureBase.toByteArray().hash()
        val signedDataBase64 = requestSigner.signToBase64(data)

        return chain.request().newBuilder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("X-AuthValidUnTillTimestamp", validUntil)
                .addHeader("X-AuthPublicKey", requestSigner.accountId)
                .addHeader("X-AuthSignature", signedDataBase64)
                .build()
    }
}