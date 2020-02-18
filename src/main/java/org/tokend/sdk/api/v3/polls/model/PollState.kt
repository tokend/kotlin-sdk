package org.tokend.sdk.api.v3.polls.model

enum class PollState(val value: Int) {
    OPEN(1),
    PASSED(2),
    FAILED(3),
    CANCELLED(4);

    companion object {
        @JvmStatic
        fun fromValue(value: Int): PollState {
            return when (value) {
                OPEN.value -> OPEN
                PASSED.value -> PASSED
                FAILED.value -> FAILED
                CANCELLED.value -> CANCELLED
                else -> throw IllegalArgumentException("There is no state with value '$value'")
            }
        }

        @JvmStatic
        fun fromName(name: String): PollState {
            return when (name.toUpperCase()) {
                OPEN.name -> OPEN
                PASSED.name -> PASSED
                FAILED.name -> FAILED
                CANCELLED.name -> CANCELLED
                else -> throw IllegalArgumentException("There is no state with name '$name'")
            }
        }
    }
}