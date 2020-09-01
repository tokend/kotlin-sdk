package org.tokend.sdk.api.integrations.invitations.model

import java.util.*


enum class InvitationEventType {
    CREATE_INVITATION,
    UNPAID_INVITATION,
    REDEEM_INVITATION,
    ACCEPT_INVITATION,
    CANCEL_INVITATION,
    WAIT_INVITATION,
    UPCOMING_INVITATION,
    EXPIRED_INVITATION,
    ENDED_INVITATION,
    ARRIVED_INVITATION,
    POSTPONED_INVITATION,
    AUTHORIZED_INVITATION,
    REJECTED_INVITATION,
    EXITED_INVITATION;

    val value: Int = ordinal

    companion object {
        fun fromValue(value: Int): InvitationEventType {
            return values().getOrNull(value)
                    ?: throw  IllegalArgumentException("There is no invitation event type with value $value")
        }

        fun fromName(name: String): InvitationEventType {
            val formattedName = name.toUpperCase(Locale.ENGLISH)
                    .substringBefore("-EVENT")
                    .replace('-', '_')
            return valueOf(formattedName)
        }
    }
}