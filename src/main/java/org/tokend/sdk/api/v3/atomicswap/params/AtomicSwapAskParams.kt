package org.tokend.sdk.api.v3.atomicswap.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

/**
 * @see AtomicSwapAskParams.Includes
 */
open class AtomicSwapAskParams(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val BASE_BALANCE = "base_balance"
            const val OWNER = "owner"
            const val BASE_ASSET = "base_asset"
            const val QUOTE_ASSETS = "quote_assets"
        }
    }
}