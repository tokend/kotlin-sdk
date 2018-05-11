package org.tokend.sdk.api.tfa

import com.google.gson.JsonParser
import okhttp3.Interceptor
import okhttp3.Response
import org.tokend.sdk.api.ApiFactory
import org.tokend.sdk.api.responses.ServerError
import org.tokend.sdk.federation.NeedTfaException
import java.net.HttpURLConnection
import java.util.concurrent.CancellationException
import java.util.concurrent.CountDownLatch

/**
 * Catches and handles TFA errors.
 */
class TfaOkHttpInterceptor(
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
                        throw CancellationException()
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

    private fun extractTfaException(response: Response): NeedTfaException? {
        val responseString = response.peekBody(response.body().contentLength()).string()
        val responseJson = JsonParser().parse(responseString).asJsonObject
        val errors = responseJson["errors"]?.asJsonArray

        if (errors != null && errors.size() > 0) {
            val error = ApiFactory.getBaseGson().fromJson(errors[0], ServerError::class.java)
            return NeedTfaException.fromError(error)
        }

        return null
    }
}