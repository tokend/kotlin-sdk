package org.tokend.sdk.api.base

import org.tokend.sdk.api.base.model.ApiResponse

interface ApiRequest<T> {
    fun execute(): ApiResponse<T>

    fun executeAsync(callback: ApiCallback<T>)

    fun cancel()

    fun isCanceled(): Boolean
}