package org.tokend.sdk.api.users.model

import com.google.gson.annotations.SerializedName

open class CreateUserRequestBody(@SerializedName("type") val type: String)