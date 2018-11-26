package org.tokend.sdk.api.fees.model

import com.google.gson.annotations.SerializedName

class FeesResponse(
        @SerializedName("fees")
        val entries : Map<String, List<Fee>>?
)