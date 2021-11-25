package org.tokend.sdk.tfa

import org.tokend.sdk.api.base.model.ForbiddenException
import org.tokend.sdk.api.base.model.ServerError
import org.tokend.sdk.api.tfa.model.TfaFactor

open class InvalidOtpException : Exception()

open class NeedTfaException(
    val factorId: Long,
    val factorType: TfaFactor.Type,
    val token: String,
    val keychainData: String,
    val salt: String,
    val walletId: String,
    val messengerBotUrl: String
) : ForbiddenException("tfa_required", "Need $factorType TFA") {

    companion object {
        private const val TOKEN = "token"
        private const val FACTOR_ID = "factor_id"
        private const val FACTOR_TYPE = "factor_type"
        private const val KEYCHAIN_DATA = "keychain_data"
        private const val SALT = "salt"
        private const val WALLET_ID = "wallet_id"
        private const val MESSENGER_BOT_URL = "bot_url"

        @JvmStatic
        fun fromError(error: ServerError): NeedTfaException? {
            if (error.detail?.contains("factor") == true) {
                val meta = error.meta

                return NeedTfaException(
                    meta?.get(FACTOR_ID)?.asLong() ?: 0L,
                    (meta?.get(FACTOR_TYPE)?.asText()).toString()
                        .let { TfaFactor.Type.fromLiteral(it) },
                    (meta?.get(TOKEN)?.asText()).toString(),
                    (meta?.get(KEYCHAIN_DATA)?.asText()).toString(),
                    (meta?.get(SALT)?.asText()).toString(),
                    (meta?.get(WALLET_ID)?.asText()).toString(),
                    (meta?.get(MESSENGER_BOT_URL)?.asText()).toString()
                )
            }

            return null
        }
    }
}