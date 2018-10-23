package org.tokend.sdk.api.accounts.params

import org.tokend.sdk.api.accounts.AccountsApi
import org.tokend.sdk.api.base.params.PagingParamsHolder
import org.tokend.sdk.api.base.params.QueryParams
import org.tokend.sdk.api.base.params.PagingParams

/**
 * Need to make a query to get pending offers.
 * @see [AccountsApi.getPendingOffers]
 */
class OffersParams(
        val pagingParams: PagingParams? = null,
        val baseAsset: String? = null,
        val quoteAsset: String? = null,
        val orderBookId: Long? = null,
        val isBuy: Boolean? = null,
        val onlyPrimary: Boolean? = null
) : QueryParams, PagingParamsHolder {
    override val order = pagingParams?.order
    override val cursor = pagingParams?.cursor
    override val limit = pagingParams?.limit

    override fun map(): Map<String, Any> {
        return mutableMapOf<String, Any>().apply {
            pagingParams?.also { putAll(it.map()) }
            baseAsset?.also { put("base_asset", it) }
            quoteAsset?.also { put("quote_asset", it) }
            orderBookId?.also { put("order_book_id", it) }
            isBuy?.also { put("is_buy", it) }
            onlyPrimary?.also { put("only_primary", it) }
        }
    }
}