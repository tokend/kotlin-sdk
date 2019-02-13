package org.tokend.sdk.api.v2.orderbook

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.generated.resources.OrderBookEntryResource
import org.tokend.sdk.api.v2.orderbook.params.OrderBookPageParams

open class OrderBooksApi(
        protected open val orderBooksService: OrderBooksService
) {
    /**
     * Returns the list page of the placed offers without private details.
     * @param id ID of the order book: 0 for secondary market, sale ID - otherwise. 0 by default
     */
    open fun getById(id: Long = 0,
                     params: OrderBookPageParams? = null): ApiRequest<DataPage<OrderBookEntryResource>> {
        return MappedRetrofitApiRequest(
                orderBooksService.getById(id.toString(), params.map()),
                DataPage.Companion::fromPageDocument
        )
    }
}