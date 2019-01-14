package org.tokend.sdk.api.v2.balances

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.v2.balances.model.BalanceResource
import org.tokend.sdk.api.v2.balances.params.BalanceParams
import org.tokend.sdk.api.v2.balances.params.BalancesPageParams

open class BalancesApi(
        protected val balancesService: BalancesService
) {
    open fun get(params: BalancesPageParams? = null): ApiRequest<DataPage<BalanceResource>> {
        return MappedRetrofitApiRequest(
                balancesService.getBalances(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    open fun getById(id: String,
                     params: BalanceParams? = null): ApiRequest<BalanceResource> {
        return MappedRetrofitApiRequest(
                balancesService.getBalanceById(
                        id,
                        params.map()
                ),
                JSONAPIDocument<BalanceResource>::get
        )
    }
}