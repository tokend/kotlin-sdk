package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.BigDecimalUtil
import java.math.BigDecimal

/**
 * Created by Oleg Koretsky on 2/7/18.
 */
data class BalanceDetails(
        @SerializedName("balance")
        val balanceString: String?,
        @SerializedName("locked")
        val lockedString: String?,
        @SerializedName("asset_details")
        val assetDetails: Asset? = null,
        @SerializedName("converted_balance")
        private val convertedBalanceString: String?,
        @SerializedName("converted_locked")
        private val convertedLockedString: String?,
        @SerializedName("offers")
        var offers: List<Offer>? = null,
        @SerializedName("balance_id")
        val balanceId: String? = null) {

    val asset: String
        get() = assetDetails?.code ?: ""

    val balance: BigDecimal
        get() = BigDecimalUtil.valueOf(balanceString)
    val convertedBalance: BigDecimal
        get() = BigDecimalUtil.valueOf(convertedBalanceString)
    val convertedLocked: BigDecimal
        get() = BigDecimalUtil.valueOf(convertedLockedString)
}