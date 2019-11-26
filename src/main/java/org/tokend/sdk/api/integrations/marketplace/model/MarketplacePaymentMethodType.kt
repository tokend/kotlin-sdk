package org.tokend.sdk.api.integrations.marketplace.model

enum class MarketplacePaymentMethodType(val value: Int) {
    FORBILL(1) {
        override fun toString() = "4BILL"
    },
    COINPAYMENTS(2),
    INTERNAL(3);

    companion object {
        @JvmStatic
        fun fromValue(value: Int): MarketplacePaymentMethodType {
            return when (value) {
                FORBILL.value -> FORBILL
                COINPAYMENTS.value -> COINPAYMENTS
                INTERNAL.value -> INTERNAL
                else -> throw IllegalArgumentException("There is no payment method type with value '$value'")
            }
        }

        @JvmStatic
        fun fromName(name: String): MarketplacePaymentMethodType {
            return when (name.toUpperCase()) {
                FORBILL.toString() -> FORBILL
                COINPAYMENTS.toString() -> COINPAYMENTS
                INTERNAL.toString() -> INTERNAL
                else -> throw IllegalArgumentException("There is no payment method type with name '$name'")
            }
        }
    }
}