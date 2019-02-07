package org.tokend.sdk.api.accounts.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

open class Participant(@SerializedName("account_id") val accountId: String? = null,
                       @SerializedName("balance_id") val balanceId: String? = null,
                       @SerializedName("nickname") val nickname: String? = null,
                       @SerializedName("effects") val effects: List<Effect>? = null) {

    class Effect(@SerializedName("base_asset")
                 val baseAsset: String? = null,
                 @SerializedName("is_buy")
                 val isBuy: Boolean = false,
                 @SerializedName("quote_asset")
                 val quoteAsset: String? = null,
                 @SerializedName("matches")
                 val matches: List<Match>? = null) {

        class Match(@SerializedName("base_amount")
                    val baseAmount: BigDecimal,
                    @SerializedName("fee_paid")
                    val fee: BigDecimal,
                    @SerializedName("price")
                    val price: BigDecimal,
                    @SerializedName("quote_amount")
                    val quoteAmount: BigDecimal)

    }
}