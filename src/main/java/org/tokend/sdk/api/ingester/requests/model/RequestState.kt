package org.tokend.sdk.api.ingester.requests.model;

enum class RequestState(val value: Int) {
    PENDING(1),
    CANCELLED(2),
    APPROVED(3),
    REJECTED(4),
    PERMANENTLY_REJECTED(5);

    companion object {
        @JvmStatic
        fun fromValue(i: Int): RequestState {
            return values().find { it.value == i }
                    ?: throw IllegalStateException("No state found for value $i")
        }
    }
}