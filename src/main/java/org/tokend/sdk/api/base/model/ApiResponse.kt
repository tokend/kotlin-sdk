package org.tokend.sdk.api.base.model

/**
 * API response holder.
 * @param value response value
 */
class ApiResponse<T>(
        private val value: T?
) {
    /**
     * @return response value.
     * @throws [IllegalStateException] f response type is [Void] or if there is no value
     * @see hasValue
     */
    fun get(): T {
        return value
                ?: throw IllegalStateException("Response has no value")
    }

    /**
     * @return true if there is a value, false otherwise.
     */
    fun hasValue(): Boolean {
        return value != null && value !is Void && value !is Unit
    }
}