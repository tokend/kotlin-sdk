package org.tokend.sdk.api.models.transactions

enum class TransactionType(val literal: String) {
    PAYMENT("payment"),
    ISSUANCE("create_issuance_request"),
    WITHDRAWAL("create_withdrawal_request"),
    INVESTMENT("check_sale_state"),
    OFFER_MATCH("manage_offer"),
    UNKNOWN("unknown");

    companion object {
        @JvmStatic
        fun fromLiteral(literal: String?): TransactionType {
            literal ?: return UNKNOWN
            return values().find { it.literal == literal } ?: UNKNOWN
        }
    }
}