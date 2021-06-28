package org.tokend.sdk.api.trades.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.math.BigDecimal
import java.util.*

/**
 * Trading offer.
 *
 * @param baseAsset base asset code of the pair
 * @param quoteAsset quote asset code of the pair
 * @param baseAmount amount of base asset
 * @param price offer price, how many quotes are in one base
 * @param isBuy true if offer buys base for quote, false otherwise
 * @param quoteAmount amount of quote asset. If not set will be calculated as baseAmount * price
 * @param id offer id, 0 for new offers. If not set will be 0
 * @param orderBookId id of the order book, 0 for the secondary market, id of the sale in case
 * of investing. If not set will be 0
 * @param fee percent fee for the offer, paid in quote asset
 *
 * @see org.tokend.sdk.api.generated.resources.OfferResource
 */
@Deprecated("We are going to replace with OfferResource")
open class Offer
@JvmOverloads
constructor(
        @SerializedName("base_asset_code") val baseAsset: String,
        @SerializedName("quote_asset_code") val quoteAsset: String,
        @SerializedName("base_amount") val baseAmount: BigDecimal,
        @SerializedName("price") val price: BigDecimal,
        @SerializedName("is_buy") val isBuy: Boolean,
        @SerializedName("quote_amount") val quoteAmount: BigDecimal = baseAmount * price,
        @SerializedName("offer_id") val id: Long = 0,
        @SerializedName("order_book_id") val orderBookId: Long = 0,
        @SerializedName("paging_token") val pagingToken: String = "",
        @SerializedName("created_at") val date: Date = Date(),
        @SerializedName("fee") var fee: BigDecimal? = null,
        @SerializedName("base_balance_id") var baseBalance: String? = null,
        @SerializedName("quote_balance_id") var quoteBalance: String? = null
) : Serializable {
    override fun equals(other: Any?): Boolean {
        return other is Offer
                && other.id == this.id
                && other.orderBookId == this.orderBookId
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + orderBookId.hashCode()
        return result
    }
}