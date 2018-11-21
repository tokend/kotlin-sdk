package org.tokend.sdk.api.accounts.model.limits

import com.google.gson.annotations.SerializedName

data class LimitEntry(
        @SerializedName("limit")
        val limit: Limit,
        @SerializedName("statistics")
        val statistics: Statistics
)