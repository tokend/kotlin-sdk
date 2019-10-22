package org.tokend.sdk.api.integrations.marketplace.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

open class MarketplaceOfferPrice(
        @SerializedName("discount")
        open val discount: BigDecimal,
        @SerializedName("price_asset")
        open val assetCode: String,
        @SerializedName("total_price")
        open val totalPrice: BigDecimal
) {
}