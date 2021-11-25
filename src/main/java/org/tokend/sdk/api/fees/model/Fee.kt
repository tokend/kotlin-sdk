package org.tokend.sdk.api.fees.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.math.BigDecimal

open class Fee(
    @get:JsonProperty("fee_type")
    val feeType: Int,
    @get:JsonProperty("subtype")
    val subtype: Int,
    @get:JsonProperty("asset")
    val requestAsset: String,
    @get:JsonProperty("fee_asset")
    val asset: String,
    @get:JsonProperty("fixed")
    val fixed: BigDecimal,
    @get:JsonProperty("percent")
    val percent: BigDecimal,
    @get:JsonProperty("lower_bound")
    val lowerBound: BigDecimal,
    @get:JsonProperty("upper_bound")
    val upperBound: BigDecimal,
    @get:JsonProperty("exists")
    val exists: Boolean,
    @get:JsonProperty("account_id")
    val account_id: String
) : Serializable {

    @get:JsonIgnore
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