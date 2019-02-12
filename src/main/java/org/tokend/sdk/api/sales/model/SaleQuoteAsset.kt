package org.tokend.sdk.api.sales.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.generated.resources.SaleQuoteAssetResource
import org.tokend.sdk.utils.BigDecimalUtil
import java.math.BigDecimal

/**
 * @see org.tokend.sdk.api.generated.resources.SaleQuoteAssetResource
 */
@Deprecated("We are going to replace with SaleQuoteAssetResource")
open class SaleQuoteAsset(@SerializedName("asset")
                          @JsonProperty("asset")
                          val code: String,
                          @SerializedName("price")
                          @JsonProperty("price")
                          private val priceString: String? = null,
                          @SerializedName("current_cap")
                          @JsonProperty("current_cap")
                          private val currentCapString: String? = null,
                          @SerializedName("total_current_cap")
                          @JsonProperty("total_current_cap")
                          private val totalCurrentCapString: String? = null,
                          @SerializedName("hard_cap")
                          @JsonProperty("hard_cap")
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