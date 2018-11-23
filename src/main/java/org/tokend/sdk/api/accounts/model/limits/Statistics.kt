package org.tokend.sdk.api.accounts.model.limits

import com.google.gson.annotations.SerializedName
import org.tokend.wallet.xdr.StatsOpType
import java.math.BigDecimal
import java.util.*

class Statistics(
        @SerializedName("id")
        val id: Long,
        @SerializedName("account_id")
        val accountId: String,
        @SerializedName("stats_op_type")
        val statsOpTypeI: Int,
        @SerializedName("asset_code")
        val asset: String,
        @SerializedName("is_convert_needed")
        val isConvertNeeded: Boolean,
        @SerializedName("daily_outcome")
        val daily: BigDecimal,
        @SerializedName("weekly_outcome")
        val weekly: BigDecimal,
        @SerializedName("monthly_outcome")
        val monthly: BigDecimal,
        @SerializedName("annual_outcome")
        val annual: BigDecimal,
        @SerializedName("updated_at")
        val updatedAtTimestamp: Long
) {
    val updatedAt: Date
        get() = Date(updatedAtTimestamp * 1000)

    val statsOpType: StatsOpType
        get() = StatsOpType.values().find { it.value == statsOpTypeI }!!
}