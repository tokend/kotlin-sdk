package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.base.factory.GsonFactory
import java.lang.reflect.Type

open class Blob(
        @SerializedName("id") val id: String,
        @SerializedName("type") val type: String,
        @SerializedName("attributes") private val attributes: Attributes) {
    class Attributes(@SerializedName("value") val value: String)

    val valueString: String
        get() = attributes.value

    fun <T> getValue(typeClass: Class<T>): T {
        return GsonFactory().getBaseGson().fromJson(valueString, typeClass)
    }

    fun <T> getValue(type: Type): T {
        return GsonFactory().getBaseGson().fromJson(valueString, type)
    }

    override fun equals(other: Any?): Boolean {
        return other is Blob && other.id == this.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        const val TYPE_ASSET_DESCRIPTION = 1
        const val TYPE_FUND_OVERVIEW = 2
        const val TYPE_FUND_UPDATE = 4
        const val TYPE_NAV_UPDATE = 8
        const val TYPE_FUND_DOCUMENT = 16
        const val TYPE_ALPHA = 32
        const val TYPE_BRAVO = 64
        const val TYPE_CHARLIE = 128
        const val TYPE_DELTA = 256
        const val TYPE_TOKEN_TERMS = 512
        const val TYPE_TOKEN_METRICS = 1024
        const val TYPE_KYC_FORM = 2048
        const val TYPE_KYC_ID_DOCUMENT = 4096
        const val TYPE_KYC_POA = 8192
        const val TYPE_IDENTITY_MIND_REJECT = 16384
    }
}