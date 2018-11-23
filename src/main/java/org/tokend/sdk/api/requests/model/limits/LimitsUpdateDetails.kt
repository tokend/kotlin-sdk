package org.tokend.sdk.api.requests.model.limits

import com.google.gson.annotations.SerializedName
import org.tokend.wallet.xdr.StatsOpType
import java.math.BigDecimal

class LimitsUpdateDetails(
        @SerializedName("asset")
        val asset: String,
        @SerializedName("note")
        val note: String,
        @SerializedName("operationType")
        val operationType: String,
        @SerializedName("requestType")
        val requestType: String,
        @SerializedName("limits")
        val limits: Limits,
        @SerializedName("statsOpType")
        val statsOpTypeI: Int
) {
    class Limits(
            @SerializedName("annualOut")
            val annual: BigDecimal,
            @SerializedName("dailyOut")
            val daily: BigDecimal,
            @SerializedName("monthlyOut")
            val monthly: BigDecimal,
            @SerializedName("weeklyOut")
            val weekly: BigDecimal
    )

    constructor(asset: String,
                note: String,
                operationType: String,
                requestType: String,
                limits: Limits,
                statsOpType: StatsOpType
    ) : this(asset, note, operationType, requestType, limits, statsOpType.value)

    val statsOpType: StatsOpType
        get() = StatsOpType.values().find { it.value == statsOpTypeI }!!
}