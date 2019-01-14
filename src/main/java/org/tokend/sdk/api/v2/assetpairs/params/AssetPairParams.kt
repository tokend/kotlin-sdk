package org.tokend.sdk.api.v2.assetpairs.params

import org.tokend.sdk.api.v2.base.JsonApiQueryParams

/**
 * @see AssetPairParams.Includes
 */
open class AssetPairParams(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val BASE_ASSET = "base_asset"
            const val QUOTE_ASSET = "quote_asset"
        }
    }
}