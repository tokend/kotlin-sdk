package org.tokend.sdk.api.integrations.invoices.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.integrations.invoices.model.InvoiceState
import org.tokend.sdk.api.v3.base.PageQueryParams

class InvoicesPageParams(
        val state: InvoiceState? = null,
        val requestor: String? = null,
        val target: String? = null,
        val asset: String? = null,
        pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> = super.map().toMutableMap().apply {
        putFilter("state", state?.value)
        putFilter("requestor", requestor)
        putFilter("target", target)
        putFilter("asset", asset)
    }
}