package org.tokend.sdk.api.integrations.cards.model

enum class CardState(val value: Int) {
    ACTIVE(0),
    FREEZED(1),
    BLOCKED(2);

    companion object {
        @JvmStatic
        fun fromValue(value: Int): CardState {
            return when (value) {
                ACTIVE.value -> ACTIVE
                FREEZED.value -> FREEZED
                BLOCKED.value -> BLOCKED
                else -> throw IllegalArgumentException("There is no card with value '$value'")
            }
        }

        @JvmStatic
        fun fromName(name: String): CardState {
            return when (name.toUpperCase()) {
                ACTIVE.name -> ACTIVE
                FREEZED.name -> FREEZED
                BLOCKED.name -> BLOCKED
                else -> throw IllegalArgumentException("There is no card with name '$name'")
            }
        }
    }
}