package org.tokend.sdk.api.v3.sales

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.generated.resources.SaleResource
import org.tokend.sdk.api.v3.sales.params.SaleParamsV3
import org.tokend.sdk.api.v3.sales.params.SalesPageParamsV3

open class SalesApiV3(
        protected val salesService: SalesServiceV3
) {
    /**
     * @return sales list page
     */
    open fun get(params: SalesPageParamsV3? = null): ApiRequest<DataPage<SaleResource>> {
        return MappedRetrofitApiRequest(
                salesService.getSales(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    /**
     * @return sale by it's ID
     */
    open fun getById(id: String,
                     params: SaleParamsV3? = null): ApiRequest<SaleResource> {
        return MappedRetrofitApiRequest(
                salesService.getSaleById(
                        id,
                        params.map()
                ),
                JSONAPIDocument<SaleResource>::get
        )
    }
}