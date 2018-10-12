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
}