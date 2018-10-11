package org.tokend.sdk.api.base

interface ApiCallback<T> {
    fun onSuccess(response: ApiResponse<T>)

    fun onError(error: Throwable)
}