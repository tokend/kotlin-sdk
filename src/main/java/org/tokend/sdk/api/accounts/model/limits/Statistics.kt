package org.tokend.sdk.api.accounts.model.limits

import com.fasterxml.jackson.annotation.JsonProperty
import org.tokend.wallet.xdr.StatsOpType
import java.math.BigDecimal
import java.util.*

class Statistics(
        @JsonProperty("id")
        val id: Long,
        @JsonProperty("account_id")
        val accountId: String,
        @JsonProperty("stats_op_type")
        val statsOpTypeI: Int,
        @JsonProperty("asset_code")
        val asset: String,
        @JsonProperty("is_convert_needed")
        val isConvertNeeded: Boolean,
        @JsonProperty("daily_outcome")
        val daily: BigDecimal,
        @JsonProperty("weekly_outcome")
        val weekly: BigDecimal,
        @JsonProperty("monthly_outcome")
        val monthly: BigDecimal,
        @JsonProperty("annual_outcome")
        val annual: BigDecimal,
        @JsonProperty("updated_at")
        val updatedAtTimestamp: Long
) {
    val updatedAt: Date
        get() = Date(updatedAtTimestamp * 1000)

    val statsOpType: StatsOpType
        get() = StatsOpType.values().find { it.value == statsOpTypeI }!!
}