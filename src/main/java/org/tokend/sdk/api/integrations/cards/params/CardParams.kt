package org.tokend.sdk.api.integrations.cards.params

import org.tokend.sdk.api.integrations.cards.params.CardParams.Includes
import org.tokend.sdk.api.v3.base.JsonApiQueryParams

/**
 * @see Includes
 */
open class CardParams(
        include: Collection<String>?
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val BALANCES = "balance"
        }
    }
}