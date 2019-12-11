package org.tokend.sdk.api.ingester.balances

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.ingester.balances.params.IngesterBalanceParams
import org.tokend.sdk.api.ingester.generated.resources.BalanceResource

open class IngesterBalancesApi(
        protected val requests: CustomRequestsApi
) {
    @JvmOverloads
    open fun getById(id: String,
                     params: IngesterBalanceParams? = null): ApiRequest<BalanceResource> {
        return requests.get(
                url = "horizon/balances/$id",
                responseClass = BalanceResource::class.java,
                queryMap = params.map()
        )
    }
}