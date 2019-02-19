package org.tokend.sdk.api.v3.offers.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

/**
 * @see OfferParamsV3.Includes
 */
open class OfferParamsV3(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val BASE_ASSET = "base_asset"
            const val QUOTE_ASSET = "quote_asset"
        }
    }
}