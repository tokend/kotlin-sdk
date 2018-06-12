package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

open class AssetPair(
        @SerializedName("base") val base: String,
        @SerializedName("quote") val quote: String,
        @SerializedName("current_price") val price: BigDecimal,
        @SerializedName("physical_price") val physicalPrice: BigDecimal,
        @SerializedName("policy") val policy: Int = 0)