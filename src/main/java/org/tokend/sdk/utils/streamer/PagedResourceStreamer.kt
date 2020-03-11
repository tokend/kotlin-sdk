package org.tokend.sdk.utils.streamer

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.DataPage
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.atomic.AtomicBoolean

/***
 * Allow to stream new items of a paged resource.
 *
 * Note that streaming only makes sense if items are in chronological order
 */
abstract class PagedResourceStreamer<T> {
    protected open val streamingExecutor: ExecutorService =
            Executors.newSingleThreadExecutor { Thread(it, "StreamerThread") }

    protected var nextCursor: String? = null
    protected var currentPageIsLast = false
    protected val enqueuedFutures = LinkedList<Future<*>>()

    protected open val pollIntervalMs: Long = DEFAULT_POLL_INTERVAL_MS
    protected open val retryTimeoutMs: Long = DEFAULT_RETRY_TIMEOUT_MS

    protected val atomicIsStreaming = AtomicBoolean()

    var isStreaming: Boolean
        get() = atomicIsStreaming.get()
        private set(value) {
            atomicIsStreaming.set(value)
        }

    /**
     * Starts streaming from the very first page
     */
    fun startStreaming() {
        if (isStreaming) {
            return
        }

        nextCursor = null
        currentPageIsLast = false
        isStreaming = true
        enqueueNextPageProcessing()
    }

    /**
     * Resumes streaming from the page on which it was stopped
     */
    fun resumeStreaming() {
        if (isStreaming) {
            return
        }

        currentPageIsLast = false
        isStreaming = true
        enqueueNextPageProcessing()
    }

    /**
     * Stops streaming
     */
    fun stopStreaming() {
        isStreaming = false
        while (!enqueuedFutures.isEmpty()) {
            enqueuedFutures.pop().cancel(true)
        }
    }

    protected open fun enqueueNextPageProcessing(withRetryDelay: Boolean = false) {
        val future = streamingExecutor.submit {
            if (currentPageIsLast && !withRetryDelay) {
                Thread.sleep(pollIntervalMs)
            } else if (withRetryDelay) {
                Thread.sleep(retryTimeoutMs)
            }

            val page: DataPage<T>

            try {
                page = getPageRequest(nextCursor)
                        .execute()
                        .get()
            } catch (e: Throwable) {
                if (isErrorFatal(e)) {
                    if (isStreaming) {
                        onFatalError(e)
                    }
                    return@submit
                } else {
                    enqueueNextPageProcessing(withRetryDelay = true)
                    return@submit
                }
            }

            // We can't skip empty pages.
            if (page.items.isNotEmpty()) {
                nextCursor = page.nextCursor
            }

            currentPageIsLast = page.isLast

            if (isStreaming) {
                if (page.items.isNotEmpty()) {
                    onNewItems(page.items)
                }

                enqueueNextPageProcessing()
            }
        }

        enqueuedFutures.push(future)
        cleanUpFuturesList()
    }

    protected open fun cleanUpFuturesList() {
        enqueuedFutures.iterator().apply {
            forEach { future ->
                if (future.isDone || future.isCancelled) {
                    remove()
                }
            }
        }
    }

    /**
     * Provides api request to get [DataPage] of specific resource.
     *
     * Order must be ASC!
     */
    protected abstract fun getPageRequest(nextCursor: String?): ApiRequest<DataPage<T>>

    /**
     * Fatal errors cause streamer to stop and are delivered to [onFatalError].
     * Non-fatal errors only cause retry with delay and are not delivered anywhere
     *
     * @return true if [error] is fatal
     */
    protected abstract fun isErrorFatal(error: Throwable): Boolean

    protected abstract fun onNewItems(newItems: List<T>)

    protected open fun onFatalError(e: Throwable) {
        stopStreaming()
    }

    companion object {
        const val DEFAULT_POLL_INTERVAL_MS = 5000L
        const val DEFAULT_RETRY_TIMEOUT_MS = 5000L
    }
}