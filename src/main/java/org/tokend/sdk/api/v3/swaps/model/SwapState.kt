package org.tokend.sdk.api.v3.swaps.model

enum class SwapState(val value: Int) {
    OPEN(1),
    CLOSED(2),
    CANCELED(3);

    companion object {
        @JvmStatic
        fun fromValue(value: Int): SwapState {
            return when (value) {
                OPEN.value -> OPEN
                CLOSED.value -> CLOSED
                CANCELED.value -> CANCELED
                else -> throw IllegalArgumentException("There is no state with value '$value'")
            }
        }

        @JvmStatic
        fun fromName(name: String): SwapState {
            return when (name.toUpperCase()) {
                OPEN.name -> OPEN
                CLOSED.name -> CLOSED
                CANCELED.name -> CANCELED
                else -> throw IllegalArgumentException("There is no state with name '$name'")
            }
        }
    }
}