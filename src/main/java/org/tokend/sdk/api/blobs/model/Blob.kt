package org.tokend.sdk.api.blobs.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.type.TypeReference
import org.tokend.sdk.factory.JsonApiTools

open class Blob(
    @field:JsonProperty("id")
    val id: String,
    @field:JsonProperty("type")
    val typeName: String,
    @field:JsonProperty("attributes")
    private val attributes: Attributes
) {
    class Attributes(@JsonProperty("value") val value: String)

    @JvmOverloads
    constructor(type: BlobType, value: String, id: String = "") : this(
        id = id,
        typeName = type.name.toLowerCase(),
        attributes = Attributes(
            value = value
        )
    )

    @get:JsonIgnore
    val valueString: String
        get() = attributes.value

    @get:JsonIgnore
    val type: BlobType
        get() = BlobType.fromName(typeName)

    @JsonIgnore
    fun <T> getValue(typeClass: Class<T>): T {
        return JsonApiTools.objectMapper.readValue(valueString, typeClass)
    }

    @JsonIgnore
    fun <T> getValue(type: TypeReference<T>): T {
        return JsonApiTools.objectMapper.readValue(valueString, type)
    }

    override fun equals(other: Any?): Boolean {
        return other is Blob && other.id == this.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}