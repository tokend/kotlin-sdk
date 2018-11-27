package org.tokend.sdk.api.fees.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.math.BigDecimal

open class Fee(
        @SerializedName("fee_type") val feeType: Int,
        @SerializedName("subtype") val subtype: Int,
        @SerializedName("asset") val requestAsset: String,
        @SerializedName("fee_asset") val asset: String,
        @SerializedName("fixed") val fixed: BigDecimal,
        @SerializedName("percent") val percent: BigDecimal,
        @SerializedName("lower_bound") val lowerBound: BigDecimal,
        @SerializedName("upper_bound") val upperBound: BigDecimal,
        @SerializedName("exists") val exists: Boolean,
        @SerializedName("account_id") val account_id: String) : Serializable {

    val total: BigDecimal
        get() = fixed + percent

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Fee) return false

        if (feeType != other.feeType) return false
        if (requestAsset != other.requestAsset) return false
        if (asset != other.asset) return false
        if (fixed != other.fixed) return false
        if (percent != other.percent) return false
        if (lowerBound != other.lowerBound) return false

        return true
    }

    override fun hashCode(): Int {
        var result = feeType
        result = 31 * result + requestAsset.hashCode()
        result = 31 * result + asset.hashCode()
        result = 31 * result + fixed.hashCode()
        result = 31 * result + percent.hashCode()
        result = 31 * result + lowerBound.hashCode()
        return result
    }
}