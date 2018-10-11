package org.tokend.sdk.api.base.model

class ApiResponse<T>(
        private val value: T?
) {
    fun get(): T {
        return value
                ?: throw IllegalStateException("Response has no value")
    }

    fun hasValue(): Boolean {
        return value != null
    }
}