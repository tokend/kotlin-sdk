package org.tokend.sdk.api.accounts.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.assets.model.Asset
import org.tokend.sdk.api.trades.model.Offer
import org.tokend.sdk.utils.BigDecimalUtil
import org.tokend.sdk.utils.HashCodes
import java.math.BigDecimal

open class BalanceDetails<AssetType : Asset<*>>(
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
        val assetDetails: AssetType? = null,
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

    override fun equals(other: Any?): Boolean {
        return other is BalanceDetails<*>
                && other.balanceId == this.balanceId
                && other.balance == this.balance
                && other.convertedBalance == this.convertedBalance
                && other.lockedBalance == this.lockedBalance
                && other.convertedLockedBalance == this.convertedLockedBalance
                && other.assetDetails == this.assetDetails
    }

    override fun hashCode(): Int {
        return HashCodes.ofMany(balance, balance, convertedBalance,
                lockedBalance, convertedLockedBalance, assetDetails)
    }
}