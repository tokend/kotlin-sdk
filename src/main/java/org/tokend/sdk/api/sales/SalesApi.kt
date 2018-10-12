package org.tokend.sdk.api.sales

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.models.SaleAnte
import org.tokend.sdk.api.sales.model.SimpleSale
import org.tokend.sdk.api.sales.params.AntesParams
import org.tokend.sdk.api.sales.params.SalesParams

open class SalesApi(
        protected val salesService: SalesService
) {
    open fun get(id: Long): ApiRequest<SimpleSale> {
        return SimpleRetrofitApiRequest(
                salesService.getSale(id)
        )
    }

    open fun getAll(params: SalesParams? = null): ApiRequest<DataPage<SimpleSale>> {
        return MappedRetrofitApiRequest(
                salesService.getSales(params?.map()),
                { DataPage.fromPage(it) }
        )
    }

    open fun getAntes(params: AntesParams? = null): ApiRequest<DataPage<SaleAnte>> {
        return MappedRetrofitApiRequest(
                salesService.getSaleAntes(params?.map()),
                { DataPage.fromPage(it) }
        )
    }
}