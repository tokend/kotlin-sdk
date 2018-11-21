package org.tokend.sdk.api.accounts.model.limits

import com.google.gson.annotations.SerializedName

class LimitsResponse(
        @SerializedName("limits")
        val entries: List<LimitEntry>?
)