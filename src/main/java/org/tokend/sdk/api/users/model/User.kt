package org.tokend.sdk.api.users.model

import com.google.gson.annotations.SerializedName

open class User<AttributesType : UserAttributes>(
        @SerializedName("type")
        val type: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("attributes")
        val attributes: AttributesType)
