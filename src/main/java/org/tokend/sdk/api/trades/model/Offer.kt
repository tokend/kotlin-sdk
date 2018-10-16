package org.tokend.sdk.api.trades.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.HashCodes
import java.io.Serializable
import java.math.BigDecimal
import java.util.*

open class Offer(
        @SerializedName("base_asset_code") val baseAsset: String,
        @SerializedName("quote_asset_code") val quoteAsset: String,
        @SerializedName("is_buy") val isBuy: Boolean = false,
        @SerializedName("base_amount") val baseAmount: BigDecimal = BigDecimal.ZERO,
        @SerializedName("quote_amount") val quoteAmount: BigDecimal = BigDecimal.ZERO,
        @SerializedName("price") val price: BigDecimal = BigDecimal.ONE,
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
        return HashCodes.ofMany(id, orderBookId)
    }
}