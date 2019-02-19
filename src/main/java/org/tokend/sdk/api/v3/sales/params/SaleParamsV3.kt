package org.tokend.sdk.api.v3.sales.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

/**
 * @see SaleParamsV3.Includes
 */
open class SaleParamsV3(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val BASE_ASSET = "base_asset"
            const val QUOTE_ASSET = "quote_assets"
            const val DEFAULT_QUOTE_ASSET = "default_quote_asset"
        }
    }
}