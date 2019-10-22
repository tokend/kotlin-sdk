package org.tokend.sdk.api.integrations.marketplace.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class MarketplaceBuyRequestAttributes(
        @SerializedName("amount")
        val amount: BigDecimal,
        @SerializedName("offer_id")
        val offerId: Long,
        @SerializedName("payment_method_id")
        val paymentMethodId: Long,
        @SerializedName("sender_account_id")
        val senderAccountId: String?,
        @SerializedName("sender_email")
        val senderEmail: String?,
        @SerializedName("promocode")
        val promocode: String?
) {
    init {
        if (senderAccountId == null && senderEmail == null) {
            throw IllegalArgumentException("Sender account or email must be set")
        }
    }
}