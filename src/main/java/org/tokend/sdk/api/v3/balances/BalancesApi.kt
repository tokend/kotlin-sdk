package org.tokend.sdk.api.v3.balances

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.v3.model.generated.resources.BalanceResource
import org.tokend.sdk.api.v3.model.generated.resources.ConvertedBalancesCollectionResource
import org.tokend.sdk.api.v3.balances.params.BalanceParams
import org.tokend.sdk.api.v3.balances.params.BalancesPageParams
import org.tokend.sdk.api.v3.balances.params.ConvertedBalancesParams

open class BalancesApi(
        protected val balancesService: BalancesService
) {
    @JvmOverloads
    open fun get(params: BalancesPageParams? = null): ApiRequest<DataPage<BalanceResource>> {
        return MappedRetrofitApiRequest(
                balancesService.getBalances(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    @JvmOverloads
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

    /**
     * @return list of balances converted to provided asset
     */
    @JvmOverloads
    open fun getConvertedBalances(accountId: String,
                                  assetCode: String,
                                  params: ConvertedBalancesParams? = null
    ): ApiRequest<ConvertedBalancesCollectionResource> {
        return MappedRetrofitApiRequest(
                balancesService.getConvertedBalances(
                        accountId,
                        assetCode,
                        params.map()
                ),
                JSONAPIDocument<ConvertedBalancesCollectionResource>::get
        )
    }
}