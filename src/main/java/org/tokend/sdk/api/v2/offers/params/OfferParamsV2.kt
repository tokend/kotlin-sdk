package org.tokend.sdk.api.v2.offers.params

import org.tokend.sdk.api.v2.base.JsonApiQueryParams

/**
 * @see OfferParamsV2.Includes
 */
open class OfferParamsV2(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val BASE_BALANCE = "base_balance"
            const val QUOTE_BALANCE = "quote_balance"
            const val OWNER = "owner"
            const val BASE_ASSET = "base_asset"
            const val QUOTE_ASSET = "quote_asset"
        }
    }
}