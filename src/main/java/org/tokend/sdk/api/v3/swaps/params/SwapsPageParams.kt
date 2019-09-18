package org.tokend.sdk.api.v3.swaps.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams
import org.tokend.sdk.api.v3.swaps.model.SwapState

// TODO: Complete me
class SwapsPageParams(
        val state: SwapState? = null,
        pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            state?.also { putFilter("state", it.value) }
        }
    }
}