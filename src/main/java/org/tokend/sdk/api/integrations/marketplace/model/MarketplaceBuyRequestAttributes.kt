package org.tokend.sdk.api.integrations.marketplace.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

class MarketplaceBuyRequestAttributes(
    @JsonProperty("amount")
    val amount: BigDecimal,
    @JsonProperty("offer_id")
    val offerId: Long,
    @JsonProperty("payment_method_id")
    val paymentMethodId: Long,
    @JsonProperty("sender_account_id")
    val senderAccountId: String?,
    @JsonProperty("sender_email")
    val senderEmail: String?,
    @JsonProperty("promocode")
    val promocode: String?
) {
    init {
        if (senderAccountId == null && senderEmail == null) {
            throw IllegalArgumentException("Sender account or email must be set")
        }
    }
}