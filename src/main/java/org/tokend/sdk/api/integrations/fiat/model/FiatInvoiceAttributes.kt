package org.tokend.sdk.api.integrations.fiat.model

import com.fasterxml.jackson.annotation.JsonProperty

class FiatInvoiceAttributes(
    @JsonProperty("pay_url")
    val paymentUrl: String
)