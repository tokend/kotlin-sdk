package org.tokend.sdk.tfa

import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Performs OTP verification.
 */
open class TfaVerifier(
    private val verificationService: TfaVerificationService,
    private val walletId: String,
    private val factorId: Long,
    private val token: String
) {
    interface Interface {
        /**
         * Performs OTP verification.
         * If OTP was verified successfully original request will be completed.
         */
        fun verify(
            otp: String,
            onSuccess: EmptyCallback? = null,
            onError: OptionalThrowableCallback? = null
        )

        /**
         * Informs verifier that verification will not be performed.
         * Original request will be then failed with the [java.util.concurrent.CancellationException]
         */
        fun cancelVerification()
    }

    /**
     * Communication object between TFA handler and TFA verifier.
     */
    private inner class InterfaceImpl : Interface {
        /**
         * Performs OTP verification.
         * If OTP was verified successfully original request will be completed.
         */
        override fun verify(
            otp: String,
            onSuccess: EmptyCallback?,
            onError: OptionalThrowableCallback?
        ) {
            this@TfaVerifier.verify(
                otp,
                onSuccess = {
                    this@TfaVerifier.onVerifiedCallback?.invoke()
                    onSuccess?.invoke()
                },
                onError = onError
            )
        }

        /**
         * Informs verifier that verification will not be performed.
         * Original request will be then failed with the [java.util.concurrent.CancellationException]
         */
        override fun cancelVerification() {
            this@TfaVerifier.onVerificationCancelledCallback?.invoke()
        }
    }

    constructor(
        verificationService: TfaVerificationService,
        tfaException: NeedTfaException
    ) : this(
        verificationService,
        tfaException.walletId, tfaException.factorId, tfaException.token
    )

    val verifierInterface: Interface = InterfaceImpl()

    protected var onVerifiedCallback: EmptyCallback? = null
    protected var onVerificationCancelledCallback: EmptyCallback? = null

    open fun onVerified(callback: EmptyCallback): TfaVerifier {
        onVerifiedCallback = callback
        return this
    }

    open fun onVerificationCancelled(callback: EmptyCallback): TfaVerifier {
        onVerificationCancelledCallback = callback
        return this
    }

    protected open fun verify(
        otp: String, onSuccess: EmptyCallback? = null,
        onError: OptionalThrowableCallback? = null
    ) {
        verificationService.verifyTfaFactor(
            walletId, factorId,
            DataEntity(
                AttributesEntity(
                    mapOf(
                        "token" to token,
                        "otp" to "otp"
                    )
                )
            )
        )
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