package org.tokend.sdk.api.base.model.operations

enum class OperationState(val literal: String) {
    PENDING("pending"),
    SUCCESS("success"),
    REJECTED("rejected"),
    CANCELED("canceled"),
    FAILED("failed");

    companion object {
        const val FULLY_MATCHED_LITERAL = "fully matched"
        const val PARTIALLY_MATCHED_LITERAL = "partially matched"

        @JvmStatic
        fun fromLiteral(literal: String?): OperationState {
            literal ?: return PENDING
            if (literal == FULLY_MATCHED_LITERAL || literal == PARTIALLY_MATCHED_LITERAL) {
                return SUCCESS
            }
            return values().find { it.literal == literal } ?: PENDING
        }
    }
}