package org.tokend.sdk.api.v3.orderbook

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.generated.resources.MatchResource
import org.tokend.sdk.api.generated.resources.OrderBookEntryResource
import org.tokend.sdk.api.generated.resources.OrderBookResource
import org.tokend.sdk.api.v3.orderbook.params.MatchesPageParams
import org.tokend.sdk.api.v3.orderbook.params.OrderBookPageParams
import org.tokend.sdk.api.v3.orderbook.params.OrderBookParamsV3

open class OrderBooksApi(
        protected open val orderBooksService: OrderBooksService
) {
    /**
     * Returns the list page of the placed offers without private details.
     * @param id ID of the order book: 0 for secondary market, sale ID - otherwise. 0 by default
     */
    @Deprecated("Will be replaced by /v3/order_books")
    open fun getById(id: Long = 0,
                     params: OrderBookPageParams? = null): ApiRequest<DataPage<OrderBookEntryResource>> {
        return MappedRetrofitApiRequest(
                orderBooksService.getById(id.toString(), params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    /**
     * Returns order book by given ID.
     * @param id ID of the order book i.e. "BTC:ETH:0"
     */
    open fun getById(id: String,
                     params: OrderBookParamsV3? = null): ApiRequest<OrderBookResource> {
        return MappedRetrofitApiRequest(
                orderBooksService.getByIdNew(
                        id,
                        params.map()
                ),
                JSONAPIDocument<OrderBookResource>::get
        )
    }

    /**
     * Returns order book by given asset codes and ID.
     * @param id ID of the order book: 0 for secondary market, sale ID - otherwise. 0 by default
     */
    open fun getById(baseAssetCode: String,
                     quoteAssetCode: String,
                     id: String = "0",
                     params: OrderBookParamsV3? = null): ApiRequest<OrderBookResource> {
        return getById("$baseAssetCode:$quoteAssetCode:$id", params)
    }

    /**
     * Returns a filtered list of Match with corresponding details.
     */
    open fun getMatches(params: MatchesPageParams? = null): ApiRequest<DataPage<MatchResource>> {
        return MappedRetrofitApiRequest(
                orderBooksService.getMatches(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }
}