package org.tokend.sdk.api.integrations.cards.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.integrations.cards.model.CardState
import org.tokend.sdk.api.v3.base.PageQueryParams

class CardsPageParams(
        val owner: String? = null,
        val state: CardState? = null,
        pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            putFilter("owner", owner)
            putFilter("state", state?.value)
        }
    }
}