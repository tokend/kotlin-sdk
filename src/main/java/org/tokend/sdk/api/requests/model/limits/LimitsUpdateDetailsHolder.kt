package org.tokend.sdk.api.requests.model.limits

import com.google.gson.annotations.SerializedName

class LimitsUpdateDetailsHolder(
        @SerializedName("document_hash")
        val documentHash: String,
        @SerializedName("details")
        val details: LimitsUpdateDetails
)