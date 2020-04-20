package org.tokend.sdk.api.integrations.recpayments.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams

/**
 * @see ScheduledPaymentParams.Includes
 */
class ScheduledPaymentsPageParams(
        val destinationBalance: String? = null,
        val destinationAccount: String? = null,
        val sourceAccount: String? = null,
        val sourceBalance: String? = null,
        pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            putFilter("destination_balance", destinationBalance)
            putFilter("destination_account", destinationAccount)
            putFilter("source_account", sourceAccount)
            putFilter("source_balance", sourceBalance)
        }
    }
}