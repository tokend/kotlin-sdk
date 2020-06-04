package org.tokend.sdk.api.integrations.cards.model

enum class CardState {
    ACTIVE,
    FREEZED,
    BLOCKED;

    val value: Int
        get() = ordinal + 1

    companion object {
        @JvmStatic
        fun fromValue(value: Int): CardState {
            return when (value) {
                ACTIVE.value -> ACTIVE
                FREEZED.value -> FREEZED
                BLOCKED.value -> BLOCKED
                else -> throw IllegalArgumentException("There is no card state with value '$value'")
            }
        }

        @JvmStatic
        fun fromName(name: String): CardState {
            return when (name.toUpperCase()) {
                ACTIVE.name -> ACTIVE
                FREEZED.name -> FREEZED
                BLOCKED.name -> BLOCKED
                else -> throw IllegalArgumentException("There is no card state with name '$name'")
            }
        }
    }
}