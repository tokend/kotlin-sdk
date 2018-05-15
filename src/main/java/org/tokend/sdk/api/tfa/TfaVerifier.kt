package org.tokend.sdk.api.tfa

import org.tokend.sdk.api.requests.AttributesEntity
import org.tokend.sdk.api.requests.DataEntity
import org.tokend.sdk.api.requests.models.VerifyTfaRequestBody
import org.tokend.sdk.federation.NeedTfaException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Performs OTP verification.
 */
class TfaVerifier(
        private val verificationService: TfaVerificationService,
        private val walletId: String,
        private val backendId: Long,
        private val token: String
) {
    /**
     * Communication object between TFA handler and TFA verifier.
     */
    inner class Interface {
        /**
         * Performs OTP verification.
         * If OTP was verified successfully original request will be completed.
         */
        fun verify(otp: String,
                   onSuccess: EmptyCallback? = null,
                   onError: OptionalThrowableCallback? = null) {
            this@TfaVerifier.verify(otp,
                    onSuccess = {
                        this@TfaVerifier.onVerifiedCallback?.invoke()
                        onSuccess?.invoke()
                    },
                    onError = onError)
        }

        /**
         * Informs verifier that verification will not be performed.
         * Original request will be then failed with the [java.util.concurrent.CancellationException]
         */
        fun cancelVerification() {
            this@TfaVerifier.onVerificationCancelledCallback?.invoke()
        }
    }

    constructor(verificationService: TfaVerificationService,
                tfaException: NeedTfaException) : this(verificationService,
            tfaException.walletId, tfaException.backendId, tfaException.token)

    val verifierInterface = Interface()

    protected var onVerifiedCallback: EmptyCallback? = null
    protected var onVerificationCancelledCallback: EmptyCallback? = null

    fun onVerified(callback: EmptyCallback): TfaVerifier {
        onVerifiedCallback = callback
        return this
    }

    fun onVerificationCancelled(callback: EmptyCallback): TfaVerifier {
        onVerificationCancelledCallback = callback
        return this
    }

    private fun verify(otp: String, onSuccess: EmptyCallback? = null,
                       onError: OptionalThrowableCallback? = null) {
        val attributes = VerifyTfaRequestBody(token, otp)

        verificationService.verifyTfaBackend(walletId, backendId,
                DataEntity(AttributesEntity(attributes)))
                .enqueue(object : Callback<Void> {
                    override fun onFailure(call: Call<Void>?, t: Throwable?) {
                        onError?.invoke(t)
                    }

                    override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                        if (response?.isSuccessful == true) {
                            onSuccess?.invoke()
                        } else {
                            onError?.invoke(InvalidOtpException())
                        }
                    }
                })
    }
}