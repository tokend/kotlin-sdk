package org.tokend.sdk.api.identity.model

import com.google.gson.annotations.SerializedName

class SetPassportIdRequestAttributes(
        @SerializedName("passport")
        val passportId: String
)