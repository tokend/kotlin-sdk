package org.tokend.sdk.api.integrations.marketplace.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

open class MarketplaceOfferPrice(
    @JsonProperty("discount")
    open val discount: BigDecimal,
    @JsonProperty("price_asset")
    open val assetCode: String,
    @JsonProperty("total_price")
    open val totalPrice: BigDecimal
)