package org.tokend.sdk.api.integrations.invoices.model

enum class InvoiceEventType {
    CREATED,
    UPDATED,
    REJECTED,
    CANCELLED,
    COMPLETED;

    val value: Int
        get() = ordinal

    companion object {
        @JvmStatic
        fun fromValue(value: Int): InvoiceEventType {
            return values().getOrNull(value)
                    ?: throw IllegalArgumentException("There is no invoice event type with value '$value'")
        }

        @JvmStatic
        fun fromName(name: String): InvoiceEventType {
            return valueOf(name.toUpperCase())
        }
    }
}