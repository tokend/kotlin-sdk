package org.tokend.sdk.api.v3.fees.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

/**
 * @see FeeParamsV3.Includes
 */
open class FeeParamsV3(
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