package org.tokend.sdk.api.v2.sales

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.v2.sales.model.SaleResource
import org.tokend.sdk.api.v2.sales.params.SaleParamsV2
import org.tokend.sdk.api.v2.sales.params.SalesPageParamsV2

open class SalesApiV2(
        protected val salesService: SalesServiceV2
) {
    /**
     * @return sales list page
     */
    open fun get(params: SalesPageParamsV2? = null): ApiRequest<DataPage<SaleResource>> {
        return MappedRetrofitApiRequest(
                salesService.getSales(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    /**
     * @return sale by it's ID
     */
    open fun getById(id: String,
                     params: SaleParamsV2? = null): ApiRequest<SaleResource> {
        return MappedRetrofitApiRequest(
                salesService.getSaleById(
                        id,
                        params.map()
                ),
                JSONAPIDocument<SaleResource>::get
        )
    }
}