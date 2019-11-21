package org.tokend.sdk.api.integrations.booking.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams

/**
 * @see BookingParams.Includes
 */
class BookingsPageParams(
        val participant: String? = null,
        val state: Int? = null,
        val type: Int? = null,
        pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            participant?.also { putFilter("participant", it) }
            state?.also { putFilter("state", it) }
            type?.also { putFilter("type", it) }
        }
    }
}