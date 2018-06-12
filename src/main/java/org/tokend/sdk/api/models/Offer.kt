package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName
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
        @SerializedName("paging_token") val pagingToken: String = "",
        @SerializedName("created_at") val date: Date = Date(),
        @SerializedName("fee") var fee: BigDecimal? = null,
        @SerializedName("base_balance_id") var baseBalance: String? = null,
        @SerializedName("quote_balance_id") var quoteBalance: String? = null
) : Serializable