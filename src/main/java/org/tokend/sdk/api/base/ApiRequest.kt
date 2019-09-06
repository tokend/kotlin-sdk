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
     * Executes request asynchronously in the separate thread.
     */
    fun executeAsync(onSuccess: (ApiResponse<T>) -> Unit,
                     onError: ((Throwable) -> Unit)?) = executeAsync(object : ApiCallback<T> {
        override fun onSuccess(response: ApiResponse<T>) { onSuccess(response) }

        override fun onError(error: Throwable) { onError?.invoke(error) }
    })

    /**
     * Executes request asynchronously in the separate thread.
     */
    fun executeAsync(onSuccess: (ApiResponse<T>) -> Unit) = executeAsync(onSuccess, null)

    /**
     * Cancels async request execution.
     */
    fun cancel()

    /**
     * @return true if the request has been canceled, false otherwise.
     */
    fun isCanceled(): Boolean

    /**
     * Adds request result transformation.
     * Transformations are stackable.
     *
     * @param mapper transformation function, will be invoked on execution
     */
    fun <R> map(mapper: (T) -> R): ApiRequest<R>
}