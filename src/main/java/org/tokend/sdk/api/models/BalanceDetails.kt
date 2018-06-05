package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.BigDecimalUtil
import java.math.BigDecimal

data class BalanceDetails(
        @SerializedName("balance_id")
        val balanceId: String,
        @SerializedName("balance")
        val balanceString: String,
        @SerializedName("locked")
        val lockedString: String,
        @SerializedName("converted_balance")
        private val convertedBalanceString: String,
        @SerializedName("converted_locked")
        private val convertedLockedString: String,
        @SerializedName("converted_to_asset")
        val conversionAsset: String,
        @SerializedName("asset_details")
        val assetDetails: Asset? = null,
        @SerializedName("offers")
        var offers: List<Offer>? = null) {

    val asset: String
        get() = assetDetails?.code ?: ""

    val balance: BigDecimal
        get() = BigDecimalUtil.valueOf(balanceString)
    val convertedBalance: BigDecimal
        get() = BigDecimalUtil.valueOf(convertedBalanceString)
    val lockedBalance: BigDecimal
        get() = BigDecimalUtil.valueOf(lockedString)
    val convertedLockedBalance: BigDecimal
        get() = BigDecimalUtil.valueOf(convertedLockedString)
}