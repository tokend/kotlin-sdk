package org.tokend.sdk.api.models.transactions

enum class TransactionState {
    PENDING, SUCCESS, REJECTED, CANCELED;

    companion object {
        fun fromCode(code: Int?): TransactionState {
            return when (code) {
                2 -> SUCCESS
                3 -> REJECTED
                4 -> CANCELED
                else -> PENDING
            }
        }
    }
}