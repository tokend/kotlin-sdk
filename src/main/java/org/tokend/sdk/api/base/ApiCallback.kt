package org.tokend.sdk.api.base

import org.tokend.sdk.api.base.model.ApiResponse

interface ApiCallback<T> {
    fun onSuccess(response: ApiResponse<T>)

    fun onError(error: Throwable)
}