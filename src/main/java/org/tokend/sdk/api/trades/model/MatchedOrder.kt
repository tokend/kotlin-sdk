package org.tokend.sdk.api.trades.model

import com.google.gson.annotations.SerializedName
import java.util.*

open class MatchedOrder(
		@field:SerializedName("paging_token") val pagingToken: String,
		@field:SerializedName("id") val id: Int,
		@field:SerializedName("base_asset") val baseAsset: String,
		@field:SerializedName("base_amount") val baseAmount: String,
		@field:SerializedName("quote_asset") val quoteAsset: String,
		@field:SerializedName("quote_amount") val quoteAmount: String,
		@field:SerializedName("price") val price: String,
		@field:SerializedName("created_at") val createdAt: Date
)