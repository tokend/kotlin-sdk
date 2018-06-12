package org.tokend.sdk.api.requests.models

import com.google.gson.annotations.SerializedName

open class CreateUserRequestBody(@SerializedName("type") val type: String)