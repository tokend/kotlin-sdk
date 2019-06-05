package org.tokend.sdk.api.v3.polls.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

/**
 * @see PollParams.Includes
 */
open class PollParams(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val PARTICIPATION = "participation"
            const val PARTICIPATION_VOTES = "participation.votes"
        }
    }
}