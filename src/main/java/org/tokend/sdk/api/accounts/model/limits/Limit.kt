package org.tokend.sdk.api.accounts.model.limits

import com.fasterxml.jackson.annotation.JsonProperty
import org.tokend.wallet.xdr.StatsOpType
import java.math.BigDecimal

class Limit(
        @JsonProperty("id")
        val id: Long,
        @JsonProperty("account_type")
        val accountType: Int,
        @JsonProperty("account_id")
        val accountId: String?,
        @JsonProperty("stats_op_type")
        val statsOpTypeI: Int,
        @JsonProperty("asset_code")
        val asset: String,
        @JsonProperty("is_convert_needed")
        val isConvertNeeded: Boolean,
        @JsonProperty("daily_out")
        val daily: BigDecimal,
        @JsonProperty("weekly_out")
        val weekly: BigDecimal,
        @JsonProperty("monthly_out")
        val monthly: BigDecimal,
        @JsonProperty("annual_out")
        val annual: BigDecimal
) {
    val statsOpType: StatsOpType
        get() = StatsOpType.values().find { it.value == statsOpTypeI }!!
}