package org.tokend.sdk.api.integrations.recpayments.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams

class ScheduledPaymentsPageParams(
        val destinationBalance: String? = null,
        val destinationAccount: String? = null,
        val sourceAccount: String? = null,
        pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            destinationBalance?.also { putFilter("destination_balance", it) }
            destinationAccount?.also { putFilter("destination_account", it) }
            sourceAccount?.also { putFilter("source_account", it) }
        }
    }
}