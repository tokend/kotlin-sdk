package org.tokend.sdk.api.blobs.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.factory.GsonFactory
import java.lang.reflect.Type

open class Blob(
        @SerializedName("id") val id: String,
        @SerializedName("type") val typeName: String,
        @SerializedName("attributes") private val attributes: Attributes
) {
    class Attributes(@SerializedName("value") val value: String)

    @JvmOverloads
    constructor(type: BlobType, value: String, id: String = "") : this(
            id = id,
            typeName = type.name.toLowerCase(),
            attributes = Blob.Attributes(
                    value = value
            )
    )

    val valueString: String
        get() = attributes.value

    val type: BlobType
        get() = BlobType.fromName(typeName)

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