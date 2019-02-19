package org.tokend.sdk.api.v3.assets.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

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