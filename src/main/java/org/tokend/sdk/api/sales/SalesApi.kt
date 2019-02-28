package org.tokend.sdk.api.sales

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.sales.model.SimpleSale
import org.tokend.sdk.api.sales.params.SalesParams

open class SalesApi(
        protected val salesService: SalesService
) {
    /**
     * Will return detailed information about the sale with specified ID.
     * @see <a href="https://tokend.gitlab.io/docs/?http#get-sale-by-id">Docs</a>
     *
     * @see org.tokend.sdk.api.v3.sales.SalesApiV3.getById
     */
    @Deprecated("We are going to replace with SalesApiV3.getById")
    open fun getById(id: Long): ApiRequest<SimpleSale> {
        return SimpleRetrofitApiRequest(
                salesService.getSale(id)
        )
    }

    /**
     * Will return sales that match specified filters.
     * @see <a href="https://tokend.gitlab.io/docs/?http#get-sales">Docs</a>
     *
     * @see org.tokend.sdk.api.v3.sales.SalesApiV3.get
     */
    @Deprecated("We are going to replace with SalesApiV3.get")
    open fun getAll(params: SalesParams? = null): ApiRequest<DataPage<SimpleSale>> {
        return MappedRetrofitApiRequest(
                salesService.getSales(params?.map()),
                { DataPage.fromPage(it) }
        )
    }
}