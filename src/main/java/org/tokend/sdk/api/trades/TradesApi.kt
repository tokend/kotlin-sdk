package org.tokend.sdk.api.trades

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.trades.model.Offer
import org.tokend.sdk.api.trades.params.OrderBookParams
import org.tokend.sdk.api.trades.params.OrdersParams

open class TradesApi(
        protected val tradesService: TradesService
) {
    /**
     * Will return order book – a list of active orders for given params.
     * @see <a href="https://tokend.gitlab.io/docs/?http#trades">Docs</a>
     */
    open fun getOrderBook(params: OrderBookParams): ApiRequest<DataPage<Offer>> {
        return MappedRetrofitApiRequest(
                tradesService.getOrderBook(params.map()),
                { DataPage.fromPage(it) }
        )
    }

    /**
     * Will return list of matched orders for given asset pair.
     * @see <a href="https://tokend.gitlab.io/docs/?http#get-matched-orders">Docs</a>
     */
    open fun getMatchedOrders(params: OrdersParams): ApiRequest<DataPage<Offer>> {
        return MappedRetrofitApiRequest(
                tradesService.getMatchedOrders(params.map()),
                { DataPage.fromPage(it) }
        )
    }
}