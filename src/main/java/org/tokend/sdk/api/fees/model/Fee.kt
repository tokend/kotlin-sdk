package org.tokend.sdk.api.fees.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.math.BigDecimal

open class Fee(
        @SerializedName("fee_type") val feeType: Int,
        @SerializedName("asset") val requestAsset: String,
        @SerializedName("fee_asset") val asset: String,
        @SerializedName("fixed") val fixed: BigDecimal,
        @SerializedName("percent") val percent: BigDecimal,
        @SerializedName("lower_bound") val lowerBound: BigDecimal) : Serializable {

    val total: BigDecimal
        get() = fixed + percent
}