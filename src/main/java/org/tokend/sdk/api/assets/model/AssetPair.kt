package org.tokend.sdk.api.assets.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.HashCodes
import java.math.BigDecimal

open class AssetPair(
        @SerializedName("base") val base: String,
        @SerializedName("quote") val quote: String,
        @SerializedName("current_price") val price: BigDecimal,
        @SerializedName("physical_price") val physicalPrice: BigDecimal,
        @SerializedName("policy") val policy: Int = 0) {
    override fun equals(other: Any?): Boolean {
        return other is AssetPair
                && other.base == this.base
                && other.quote == this.quote
                && other.price == this.price
                && other.physicalPrice == physicalPrice
                && other.policy == this.policy
    }

    override fun hashCode(): Int {
        return HashCodes.ofMany(base, quote, price, physicalPrice, policy)
    }
}