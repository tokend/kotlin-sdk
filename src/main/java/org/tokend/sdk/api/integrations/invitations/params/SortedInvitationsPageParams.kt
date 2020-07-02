package org.tokend.sdk.api.integrations.invitations.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.integrations.invitations.model.InvitationState

/**
 * Use - to get descending order. Use attribute or relationship name to sort by.
 * Currently, from and updated_at are supported
 */
class SortedInvitationsPageParams(
        val sort: String? = null,
        states: Collection<InvitationState>? = null,
        host: String? = null,
        guest: String? = null,
        placeAsset: String? = null,
        pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : InvitationsPageParams(states, host, guest, placeAsset, pagingParams, include) {
    override fun map(): Map<String, Any> = super.map().toMutableMap().apply {
        sort?.also { put("sort", it) }
    }
}