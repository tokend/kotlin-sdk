package org.tokend.sdk.api.requests.models

import com.google.gson.annotations.SerializedName

data class CreateTfaRequestBody(@SerializedName("type") val tfaType: String)