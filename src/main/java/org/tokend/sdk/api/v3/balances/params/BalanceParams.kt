package org.tokend.sdk.api.v3.balances.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

/**
 * @see BalanceParams.Includes
 */
open class BalanceParams(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val ACCOUNT = "account"
            const val ASSET = "asset"
        }
    }
}