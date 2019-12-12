package org.tokend.sdk.api.v3.balances

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.generated.resources.ConvertedBalancesCollectionResource
import org.tokend.sdk.api.v3.balances.params.ConvertedBalancesParams

open class BalancesApi(
        protected val balancesService: BalancesService
) {
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