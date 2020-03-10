package org.tokend.sdk.utils.streamer

interface ApiStreamerCallback<T> {
    fun onNext(items: List<T>)

    fun onError(error: Throwable)
}