package org.tokend.sdk.api.v2.sales.params

import org.tokend.sdk.api.v2.base.JsonApiQueryParams

/**
 * @see SaleParamsV2.Includes
 */
open class SaleParamsV2(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val BASE_ASSET = "base_asset"
            const val QUOTE_ASSET = "quote_assets"
        }
    }
}