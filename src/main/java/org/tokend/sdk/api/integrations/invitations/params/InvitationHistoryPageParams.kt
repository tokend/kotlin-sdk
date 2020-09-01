package org.tokend.sdk.api.integrations.invitations.params

import org.tokend.sdk.api.base.params.PagingParamsV3
import org.tokend.sdk.api.integrations.invitations.model.InvitationEventType
import org.tokend.sdk.api.v3.base.PageQueryParams

class InvitationHistoryPageParams(
        val types: Set<InvitationEventType>? = null,
        val guest: String? = null,
        val host: String? = null,
        val placeAsset: String? = null,
        val timestamp: Long? = null,
        pagingParams: PagingParamsV3? = null,
        include: Collection<String>? = null
): PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> = super.map().toMutableMap().apply {
        types?.also { types ->
            putFilter(
                    "event_type",
                    types.joinToString(",", transform = { it.value.toString() })
            )
        }
        putFilter("guest", guest)
        putFilter("host", host)
        putFilter("place", placeAsset)
        putFilter("timestamp", timestamp)
    }
}