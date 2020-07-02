package org.tokend.sdk.api.integrations.invitations.model

import java.util.*

enum class InvitationState(val value: Int) {
    UNPAID(0),
    UPCOMING(1),
    PENDING(2),
    WAITING(3),
    ACCEPTED(4),
    CANCELLED(5),
    EXPIRED(6),
    ENDED(7);

    companion object {
        @JvmStatic
        fun fromValue(value: Int): InvitationState {
            return values().find { it.value == value }
                    ?: throw IllegalArgumentException("There is no invitation state with value '$value'")
        }

        @JvmStatic
        fun fromName(name: String): InvitationState {
            return name.toUpperCase(Locale.ENGLISH).let {
                values().find { state -> state.name == it }
                        ?: throw IllegalArgumentException("There is no invitation state with name '$name'")
            }
        }
    }
}