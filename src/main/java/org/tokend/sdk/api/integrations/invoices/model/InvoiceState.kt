package org.tokend.sdk.api.integrations.invoices.model

enum class InvoiceState(val value: Int) {
    PENDING(0),
    COMPLETED(1),
    REJECTED(2),
    CANCELLED(3);

    companion object {
        @JvmStatic
        fun fromValue(value: Int): InvoiceState {
            return when (value) {
                PENDING.value -> PENDING
                COMPLETED.value -> COMPLETED
                REJECTED.value -> REJECTED
                CANCELLED.value -> CANCELLED
                else -> throw IllegalArgumentException("There is no invoice with value '$value'")
            }
        }

        @JvmStatic
        fun fromName(name: String): InvoiceState {
            return when (name.toUpperCase()) {
                PENDING.name -> PENDING
                COMPLETED.name -> COMPLETED
                REJECTED.name -> REJECTED
                CANCELLED.name -> CANCELLED
                else -> throw IllegalArgumentException("There is no invoice with name '$name'")
            }
        }
    }
}