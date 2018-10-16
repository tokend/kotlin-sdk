package org.tokend.sdk.api.base

import org.tokend.sdk.api.base.model.ApiResponse

/**
 * Represents ready to execute API request.
 */
interface ApiRequest<T> {
    /**
     * Executes request synchronously in the current thread.
     */
    fun execute(): ApiResponse<T>

    /**
     * Executes request asynchronously in the separate thread.
     */
    fun executeAsync(callback: ApiCallback<T>)

    /**
     * Cancels async request execution.
     */
    fun cancel()

    /**
     * @return true if the request has been canceled, false otherwise.
     */
    fun isCanceled(): Boolean
}