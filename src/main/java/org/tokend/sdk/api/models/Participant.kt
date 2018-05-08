package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.BigDecimalUtil
import java.math.BigDecimal

/**
 * Created by Oleg Koretsky on 11/29/17.
 */
data class Participant(@SerializedName("account_id") val accountId: String? = null,
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
                    val baseAmountString: String? = null,
                    @SerializedName("fee_paid")
                    val feeString: String? = null,
                    @SerializedName("price")
                    val priceString: String? = null,
                    @SerializedName("quote_amount")
                    val quoteAmountString: String? = null) {

            val baseAmount: BigDecimal
                get() = BigDecimalUtil.valueOf(baseAmountString)
            val fee: BigDecimal
                get() = BigDecimalUtil.valueOf(feeString)
            val quoteAmount: BigDecimal
                get() = BigDecimalUtil.valueOf(quoteAmountString)
            val price: BigDecimal
                get() = BigDecimalUtil.valueOf(priceString)
        }
    }
}