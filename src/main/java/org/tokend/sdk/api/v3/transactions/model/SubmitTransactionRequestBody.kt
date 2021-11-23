package org.tokend.sdk.api.v3.transactions.model

import com.fasterxml.jackson.annotation.JsonProperty

class SubmitTransactionRequestBody(
    @JsonProperty("tx")
    val envelopeBase64: String,
    @JsonProperty("wait_for_ingest")
    val waitForIngest: Boolean
)