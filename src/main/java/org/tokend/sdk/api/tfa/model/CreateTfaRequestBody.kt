package org.tokend.sdk.api.tfa.model

import com.google.gson.annotations.SerializedName

open class CreateTfaRequestBody(@SerializedName("type") val tfaType: String)