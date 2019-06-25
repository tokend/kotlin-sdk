package org.tokend.sdk.api.integrations.dns.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams

class ClientsPageParams(
        pagingParams: PagingParamsV2?,
        include: Collection<String>?
) : PageQueryParams(pagingParams, include) {
    class Includes {
        companion object {
            const val BALANCES = "balances"
        }
    }
}