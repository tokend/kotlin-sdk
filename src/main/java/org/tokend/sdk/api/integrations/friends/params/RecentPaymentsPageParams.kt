package org.tokend.sdk.api.integrations.friends.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams

class RecentPaymentsPageParams(
        val createdAfterUnix: Long? = null,
        pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            putFilter("after", createdAfterUnix)
        }
    }
}