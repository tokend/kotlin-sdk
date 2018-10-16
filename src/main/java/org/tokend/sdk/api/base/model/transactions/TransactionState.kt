package org.tokend.sdk.api.base.model.transactions

enum class TransactionState(val literal: String) {
    PENDING("pending"),
    SUCCESS("success"),
    REJECTED("rejected"),
    CANCELED("canceled"),
    FAILED("failed");

    companion object {
        const val FULLY_MATCHED_LITERAL = "fully matched"

        @JvmStatic
        fun fromLiteral(literal: String?): TransactionState {
            literal ?: return PENDING
            if (literal == FULLY_MATCHED_LITERAL) {
                return SUCCESS
            }
            return values().find { it.literal == literal } ?: PENDING
        }
    }
}