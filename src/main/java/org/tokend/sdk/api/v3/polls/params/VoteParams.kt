package org.tokend.sdk.api.v3.polls.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

/**
 * @see VoteParams.Includes
 */
open class VoteParams(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val POLL = "poll"
            const val POLLS = "polls"
            const val ACCOUNT = "account"
        }
    }
}