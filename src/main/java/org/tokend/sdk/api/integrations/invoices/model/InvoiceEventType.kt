package org.tokend.sdk.api.integrations.invoices.model

enum class InvoiceEventType(val value: Int) {
    CREATED(1),
    UPDATED(2),
    REJECTED(3),
    CANCELLED(4),
    COMPLETED(5);

    companion object {
        private val valuesMap = values().associateBy(InvoiceEventType::value)

        @JvmStatic
        fun fromValue(value: Int): InvoiceEventType {
            return valuesMap[value]
                    ?: throw IllegalArgumentException("There is no invoice event type with value '$value'")
        }

        @JvmStatic
        fun fromName(name: String): InvoiceEventType {
            return valueOf(name.toUpperCase())
        }
    }
}