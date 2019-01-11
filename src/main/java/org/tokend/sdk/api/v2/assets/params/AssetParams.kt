package org.tokend.sdk.api.v2.assets.params

import org.tokend.sdk.api.v2.base.JsonApiQueryParams

/**
 * @see AssetParams.Includes
 */
open class AssetParams(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val OWNER = "owner"
        }
    }
}