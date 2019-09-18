package org.tokend.sdk.api.v3.swaps.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

class SwapParams(include: Collection<String>? = null) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val SOURCE_BALANCE = "source_balance"
            const val DESTINATION_BALANCE = "destination_balance"
            const val ASSET = "asset"
        }
    }
}