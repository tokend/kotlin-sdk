package org.tokend.sdk.api.base

interface ApiRequest<T> {
    fun execute(): ApiResponse<T>

    fun executeAsync(callback: ApiCallback<T>)

    fun cancel()

    fun isCanceled(): Boolean
}