package org.tokend.sdk.api.sales.model

enum class SaleState(val value: Int) {
    OPEN(1),
    CLOSED(2),
    CANCELED(3);

    companion object {
        @JvmStatic
        fun fromValue(value: Int): SaleState {
            return when (value) {
                OPEN.value -> OPEN
                CLOSED.value -> CLOSED
                CANCELED.value -> CANCELED
                else -> throw IllegalArgumentException("There is no state with value '$value'")
            }
        }

        @JvmStatic
        fun fromName(name: String): SaleState {
            return when (name.toUpperCase()) {
                OPEN.name -> OPEN
                CLOSED.name -> CLOSED
                CANCELED.name -> CANCELED
                else -> throw IllegalArgumentException("There is no state with name '$name'")
            }
        }
    }
}