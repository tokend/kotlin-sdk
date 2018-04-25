package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.BigDecimalUtil
import java.math.BigDecimal

/**
 * Created by Oleg Koretsky on 11/28/17.
 */
data class AssetPair(
        @SerializedName("base") val code: String,
        @SerializedName("quote") val quote: String,
        @SerializedName("current_price") private val priceString: String?,
        @SerializedName("physical_price") private val physicalPriceString: String?) {

    val price: BigDecimal
        get() = BigDecimalUtil.valueOf(priceString, BigDecimal.ONE)

    val physicalPrice: BigDecimal
        get() = BigDecimalUtil.valueOf(physicalPriceString, BigDecimal.ONE)
}