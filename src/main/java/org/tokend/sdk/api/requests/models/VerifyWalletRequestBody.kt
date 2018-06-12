package org.tokend.sdk.api.requests.models

import com.google.gson.annotations.SerializedName

open class VerifyWalletRequestBody(@SerializedName("token") val token: String)