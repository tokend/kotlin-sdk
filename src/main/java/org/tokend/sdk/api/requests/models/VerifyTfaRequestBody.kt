package org.tokend.sdk.api.requests.models

import com.google.gson.annotations.SerializedName

data class VerifyTfaRequestBody(@SerializedName("token") val token: String,
                                @SerializedName("otp") val otp: String)