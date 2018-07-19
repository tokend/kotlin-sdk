package org.tokend.sdk.api.models.sale

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.BigDecimalUtil
import java.math.BigDecimal

open class SaleQuoteAsset(@SerializedName("asset")
                          val code: String,
                          @SerializedName("price")
                          private val priceString: String? = null,
                          @SerializedName("current_cap")
                          private val currentCapString: String? = null,
                          @SerializedName("total_current_cap")
                          private val totalCurrentCapString: String? = null,
                          @SerializedName("hard_cap")
                          private val hardCapString: String? = null) {

    open val price: BigDecimal
        get() = BigDecimalUtil.valueOf(priceString)
    open val currentCap: BigDecimal
        get() = BigDecimalUtil.valueOf(currentCapString)
    open val totalCurrentCap: BigDecimal
        get() = BigDecimalUtil.valueOf(totalCurrentCapString)
    open val hardCap: BigDecimal
        get() = BigDecimalUtil.valueOf(hardCapString)
}