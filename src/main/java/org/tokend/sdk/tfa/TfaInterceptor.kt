package org.tokend.sdk.tfa

import okhttp3.Interceptor
import okhttp3.Response
import org.tokend.sdk.api.base.model.ErrorBody
import java.io.InterruptedIOException
import java.net.HttpURLConnection
import java.util.concurrent.CountDownLatch

/**
 * Catches and handles TFA errors.
 */
open class TfaInterceptor(
        private val verificationService: TfaVerificationService,
        private val tfaCallback: TfaCallback?) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        while (true) {
            val request = chain.request()
            val response = chain.proceed(request)

            if (response.code() == HttpURLConnection.HTTP_FORBIDDEN) {
                val exception = extractTfaException(response)
                if (exception != null && tfaCallback != null) {
                    // Wait for TFA verification in current thread.
                    val latch = CountDownLatch(1)
                    var cancelled = false

                    val verifier = TfaVerifier(verificationService, exception)
                            .onVerified {
                                latch.countDown()
                            }
                            .onVerificationCancelled {
                                cancelled = true
                                latch.countDown()
                            }
                    tfaCallback.onTfaRequired(exception, verifier.verifierInterface)

                    latch.await()

                    if (cancelled) {
                        throw InterruptedIOException("Request was interrupted due to the " +
                                "cancelled TFA verification")
                    }
                } else if (exception != null) {
                    throw exception
                } else {
                    return response
                }
            } else {
                return response
            }
        }
    }

    protected open fun extractTfaException(response: Response): NeedTfaException? {
        val error = try {
            val responseString = response.peekBody(response.body().contentLength()).string()
            ErrorBody.fromJsonString(responseString).firstOrNull
        } catch (_: Exception) {
            null
        } ?: return null

        return try {
            NeedTfaException.fromError(error)
        } catch (_: java.lang.Exception) {
            null
        }
    }
}