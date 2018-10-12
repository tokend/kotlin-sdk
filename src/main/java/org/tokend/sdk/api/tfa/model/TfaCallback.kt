package org.tokend.sdk.api.tfa.model

import org.tokend.sdk.federation.NeedTfaException

/**
 * Used to provide TFA data to complete TFA-protected requests.
 */
interface TfaCallback {
    /**
     * Used to be called when TFA error occurred during API request and user's action
     * is required.
     * @param exception [NeedTfaException] with all required TFA error data
     * @param verifierInterface communication object between TFA error handler and TFA verifier
     */
    fun onTfaRequired(exception: NeedTfaException, verifierInterface: TfaVerifier.Interface)
}