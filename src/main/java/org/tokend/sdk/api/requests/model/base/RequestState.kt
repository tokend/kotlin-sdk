package org.tokend.sdk.api.requests.model.base

enum class RequestState(val i: Int) {
    UNKNOWN(-1),
    PENDING(1),
    CANCELLED(2),
    APPROVED(3),
    REJECTED(4),
    PERMANENTLY_REJECTED(5);

    companion object {
        @JvmStatic
        fun fromI(i: Int): RequestState {
            return values().find { it.i == i } ?: UNKNOWN
        }
    }
}