package org.tokend.sdk.api.responses

import com.google.gson.annotations.SerializedName

open class ConvertAssetsResponse {
    @SerializedName("amount")
    var amount: String? = null
}