package org.tokend.sdk.api.identity.model

import com.google.gson.annotations.SerializedName

class SetPhoneRequestAttributes(
        @SerializedName("phone")
        val phoneNumber: String
)