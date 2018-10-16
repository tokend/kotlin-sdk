package org.tokend.sdk.api.base.model.transactions

enum class TransactionType(val literal: String) {
    PAYMENT("payment"),
    ISSUANCE("create_issuance_request"),
    WITHDRAWAL("create_withdrawal_request"),
    INVESTMENT("check_sale_state"),
    OFFER_MATCH("manage_offer"),
    UNKNOWN("unknown");

    companion object {
        const val PAYMENT_V2_LITERAL = "payment_v2"
        @JvmStatic
        fun fromLiteral(literal: String?): TransactionType {
            literal ?: return UNKNOWN
            if (literal == PAYMENT_V2_LITERAL) {
                return PAYMENT
            }
            return values().find { it.literal == literal } ?: UNKNOWN
        }
    }
}