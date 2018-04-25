package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.ApiDateUtil
import java.util.*

/**
 * Created by Oleg Koretsky on 1/5/18.
 */
data class Offer(
        @SerializedName("id") val id: String? = null,
        @SerializedName("paging_token") val pagingToken: String? = null,
        @SerializedName("offer_id") val offerId: Long? = null,
        @SerializedName("base_balance_id") val baseBalance: String? = null,
        @SerializedName("quote_balance_id") val quoteBalance: String? = null,
        @SerializedName("base_asset_code") val baseAsset: String? = null,
        @SerializedName("quote_asset_code") val quoteAsset: String? = null,
        @SerializedName("fee") val fee: String? = null,
        @SerializedName("is_buy") val isBuy: Boolean? = null,
        @SerializedName("base_amount") val baseAmount: String? = null,
        @SerializedName("quote_amount") val quoteAmount: String? = null,
        @SerializedName("price") val price: String? = null,
        @SerializedName("created_at") val createdAt: String? = null) {

    val date: Date
        get() = ApiDateUtil.tryParseDate(this.createdAt)
}