package org.tokend.sdk.api.requests.models

import com.google.gson.annotations.SerializedName

data class CreateUserRequestBody(@SerializedName("type") val type: String)