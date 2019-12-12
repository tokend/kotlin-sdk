package org.tokend.sdk.api.ingester.transactions.model

import com.google.gson.annotations.SerializedName

class SubmitTransactionRequestBody(
        @SerializedName("tx")
        val envelopeBase64: String,
        @SerializedName("wait_for_ingest")
        val waitForIngest: Boolean
)