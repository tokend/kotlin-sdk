package org.tokend.sdk.api.base

import org.tokend.sdk.api.base.model.ApiResponse

/**
 * Result receiver for async API request.
 */
interface ApiCallback<T> {
    fun onSuccess(response: ApiResponse<T>)

    fun onError(error: Throwable)
}