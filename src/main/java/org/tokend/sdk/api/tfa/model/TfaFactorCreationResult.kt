package org.tokend.sdk.api.tfa.model

/**
 * Result of the TFA factor creation
 */
data class TfaFactorCreationResult(
        /**
         * Created factor
         */
        val newFactor: TfaFactor,

        /**
         * Special attributes required for factor confirmation
         */
        val confirmationAttributes: Map<String, Any>
)