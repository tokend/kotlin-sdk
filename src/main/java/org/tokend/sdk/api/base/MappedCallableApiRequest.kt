package org.tokend.sdk.api.base

import org.tokend.sdk.api.base.model.ApiResponse
import java.io.InterruptedIOException
import java.util.concurrent.Executors

open class MappedCallableApiRequest<CallType, ResultType>(
        protected val callable: () -> CallType?,
        protected val responseMapper: (CallType) -> ResultType,
        protected val errorMapper: ((Throwable) -> Throwable)? = null
) : ApiRequest<ResultType> {
    protected val executorService = Executors.newSingleThreadExecutor()
    protected var asyncCallback: ApiCallback<ResultType>? = null

    override fun execute(): ApiResponse<ResultType> {
        return try {
            val result = callable.invoke()

            if (Thread.currentThread().isInterrupted || isCanceled()) {
                throw InterruptedIOException("Canceled by user")
            }

            if (result != null && result !is Unit && result !is Void) {
                ApiResponse(mapResponse(result))
            } else {
                ApiResponse(null)
            }
        } catch (e: Exception) {
            throw mapError(e)
        }
    }

    override fun executeAsync(callback: ApiCallback<ResultType>) {
        synchronized(this) {
            if (asyncCallback != null) {
                return
            }

            asyncCallback = callback

            executorService.submit {
                // No mapping required, done in .execute()
                try {
                    val result = execute()
                    asyncCallback?.onSuccess(result)
                } catch (e: Exception) {
                    asyncCallback?.onError(e)
                }
            }

            null
        }
    }

    override fun cancel() {
        synchronized(this) {
            executorService.shutdownNow()
            asyncCallback?.onError(mapError(InterruptedIOException("Canceled by user")))
            asyncCallback = null
        }
    }

    override fun isCanceled(): Boolean {
        synchronized(this) {
            return executorService.isShutdown
        }
    }

    override fun <R> map(mapper: (ResultType) -> R): ApiRequest<R> {
        val newMapper: (CallType) -> R = {
            responseMapper.invoke(it).let(mapper)
        }

        return MappedCallableApiRequest(callable, newMapper, errorMapper)
    }

    protected open fun mapError(error: Throwable): Throwable {
        return errorMapper?.invoke(error) ?: error
    }

    protected open fun mapResponse(response: CallType): ResultType {
        return if (response is Void || response == null)
            response as ResultType
        else
            responseMapper(response)
    }
}