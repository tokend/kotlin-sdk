package org.tokend.sdk.api.v2.fees.params

import org.tokend.sdk.api.v2.base.JsonApiQueryParams

/**
 * @see FeeParamsV2.Includes
 */
open class FeeParamsV2(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val ACCOUNT = "account"
            const val ASSET = " b"
            const val FEE_ASSET = "fee_asset"
        }
    }
}