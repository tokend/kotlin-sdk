package org.tokend.sdk.api.accounts.model.limits

import com.google.gson.annotations.SerializedName
import org.tokend.wallet.xdr.StatsOpType
import java.math.BigDecimal

class Limit(
        @SerializedName("id")
        val id: Long,
        @SerializedName("account_type")
        val accountType: Int,
        @SerializedName("account_id")
        val accountId: String?,
        @SerializedName("stats_op_type")
        val statsOpTypeI: Int,
        @SerializedName("asset_code")
        val asset: String,
        @SerializedName("is_convert_needed")
        val isConvertNeeded: Boolean,
        @SerializedName("daily_out")
        val daily: BigDecimal,
        @SerializedName("weekly_out")
        val weekly: BigDecimal,
        @SerializedName("monthly_out")
        val monthly: BigDecimal,
        @SerializedName("annual_out")
        val annual: BigDecimal
) {
    val statsOpType: StatsOpType
        get() = StatsOpType.values().find { it.value == statsOpTypeI }!!
}