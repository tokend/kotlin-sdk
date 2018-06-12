package org.tokend.sdk.api.requests.models

import com.google.gson.annotations.SerializedName

open class VerifyTfaRequestBody(@SerializedName("token") val token: String,
                                @SerializedName("otp") val otp: String)