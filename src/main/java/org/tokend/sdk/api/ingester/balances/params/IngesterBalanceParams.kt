package org.tokend.sdk.api.ingester.balances.params

import org.tokend.sdk.api.ingester.balances.params.IngesterBalanceParams.Includes
import org.tokend.sdk.api.v3.base.JsonApiQueryParams

/**
 * @see Includes
 */
open class IngesterBalanceParams(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    companion object Includes {
        const val ASSET = "asset"
        const val STATE = "state"
    }
}