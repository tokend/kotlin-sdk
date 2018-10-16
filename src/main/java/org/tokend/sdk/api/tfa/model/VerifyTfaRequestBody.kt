package org.tokend.sdk.api.tfa.model

import com.google.gson.annotations.SerializedName

open class VerifyTfaRequestBody(@SerializedName("token") val token: String,
                                @SerializedName("otp") val otp: String)