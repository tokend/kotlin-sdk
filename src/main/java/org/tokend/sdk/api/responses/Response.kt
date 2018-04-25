package org.tokend.sdk.api.responses

import okhttp3.internal.http2.Header

abstract class Response {
    var rateLimitLimit: Int = 0
        protected set

    var rateLimitRemaining: Int = 0
        protected set

    var rateLimitReset: Int = 0
        protected set

    //Todo: test me
    fun setHeaders(limit: Header?, remaining: Header?, reset: Header?) {
        limit?.let { header -> this.rateLimitLimit = header.value.utf8().toInt() }
        remaining?.let { header -> this.rateLimitRemaining = header.value.utf8().toInt() }
        reset?.let { header -> this.rateLimitReset = header.value.utf8().toInt() }
    }
}
