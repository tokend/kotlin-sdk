package org.tokend.sdk.tfa

import org.tokend.sdk.api.base.model.ForbiddenException
import org.tokend.sdk.api.base.model.ServerError
import org.tokend.sdk.api.tfa.model.TfaFactor

open class InvalidOtpException : Exception()

open class NeedTfaException(val factorId: Long,
                            val factorType: TfaFactor.Type,
                            val token: String,
                            val keychainData: String,
                            val salt: String,
                            val walletId: String)
    : ForbiddenException("tfa_required", "Need $factorType TFA") {

    companion object {
        private const val TOKEN = "token"
        private const val FACTOR_ID = "factor_id"
        private const val FACTOR_TYPE = "factor_type"
        private const val KEYCHAIN_DATA = "keychain_data"
        private const val SALT = "salt"
        private const val WALLET_ID = "wallet_id"

        @JvmStatic
        fun fromError(error: ServerError): NeedTfaException? {
            if (error.detail?.contains("factor") == true) {
                val meta = error.meta?.asJsonObject

                return NeedTfaException(
                        meta?.get(FACTOR_ID)?.asLong ?: 0L,
                        (meta?.get(FACTOR_TYPE)?.asString).toString()
                                .let { TfaFactor.Type.fromLiteral(it) },
                        (meta?.get(TOKEN)?.asString).toString(),
                        (meta?.get(KEYCHAIN_DATA)?.asString).toString(),
                        (meta?.get(SALT)?.asString).toString(),
                        (meta?.get(WALLET_ID)?.asString).toString())
            }

            return null
        }
    }
}