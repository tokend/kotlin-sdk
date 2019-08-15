package org.tokend.sdk.api.integrations.fiat.model

import com.google.gson.annotations.SerializedName

class FiatInvoiceAttributes(
        @SerializedName("pay_url")
        val paymentUrl: String
)