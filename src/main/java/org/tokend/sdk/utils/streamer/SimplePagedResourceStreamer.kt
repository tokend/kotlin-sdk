package org.tokend.sdk.utils.streamer

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.DataPage
import java.util.function.Predicate

/**
 * Simple callback-based implementation of [PagedResourceStreamer]
 *
 * @see [PagedResourceStreamer.isErrorFatal]
 */
class SimplePagedResourceStreamer<T>(
        private val callback: ApiStreamerCallback<T>,
        private val requestProvider: (nextCursor: String?) -> ApiRequest<DataPage<T>>,
        private val fatalErrorPredicate: Predicate<Throwable> = Predicate { false },
        override val pollIntervalMs: Long = DEFAULT_POLL_INTERVAL_MS,
        override val retryTimeoutMs: Long = DEFAULT_RETRY_TIMEOUT_MS
) : PagedResourceStreamer<T>() {
    constructor(
            onNext: (List<T>) -> Unit,
            onError: (Throwable) -> Unit,
            requestProvider: (nextCursor: String?) -> ApiRequest<DataPage<T>>,
            fatalErrorPredicate: Predicate<Throwable> = Predicate { false },
            pollIntervalMs: Long = DEFAULT_POLL_INTERVAL_MS,
            retryIntervalMs: Long = DEFAULT_RETRY_TIMEOUT_MS
    ): this(
            object : ApiStreamerCallback<T> {
                override fun onNext(items: List<T>) {
                    onNext(items)
                }

                override fun onError(error: Throwable) {
                    onError(error)
                }
            },
            requestProvider,
            fatalErrorPredicate,
            pollIntervalMs,
            retryIntervalMs
    )

    override fun getPageRequest(nextCursor: String?) =
            requestProvider.invoke(nextCursor)

    override fun isErrorFatal(error: Throwable) =
            fatalErrorPredicate.test(error)

    override fun onNewItems(newItems: List<T>) {
        callback.onNext(newItems)
    }

    override fun onFatalError(e: Throwable) {
        super.onFatalError(e)
        callback.onError(e)
    }
}