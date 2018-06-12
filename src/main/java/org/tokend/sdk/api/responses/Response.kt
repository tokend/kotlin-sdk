package org.tokend.sdk.api.responses

abstract class Response {
    var rateLimitLimit: Int = 0
        protected set

    var rateLimitRemaining: Int = 0
        protected set

    var rateLimitReset: Int = 0
        protected set
}
