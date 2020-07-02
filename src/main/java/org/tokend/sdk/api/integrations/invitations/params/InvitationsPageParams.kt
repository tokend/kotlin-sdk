package org.tokend.sdk.api.integrations.invitations.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.integrations.invitations.model.InvitationState
import org.tokend.sdk.api.v3.base.PageQueryParams

open class InvitationsPageParams(
        val states: Collection<InvitationState>? = null,
        val host: String? = null,
        val guest: String? = null,
        val placeAsset: String? = null,
        pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> = super.map().toMutableMap().apply {
        states?.also { putFilter("states", it.joinToString(",")) }
        putFilter("host", host)
        putFilter("guest", guest)
        putFilter("place", placeAsset)
    }
}