package org.tokend.sdk.api.requests.models

import com.google.gson.annotations.SerializedName

open class CreateTfaRequestBody(@SerializedName("type") val tfaType: String)