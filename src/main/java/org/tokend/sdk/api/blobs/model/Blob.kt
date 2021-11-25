package org.tokend.sdk.api.blobs.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.tokend.sdk.factory.JsonApiTools

class Blob(
    val id: String,
    val typeName: String,
    val valueString: String
) {
    /**
     * @param value any object that will be stringified to JSON using [JsonApiTools.objectMapper]
     */
    constructor(
        typeName: String,
        value: Any
    ) : this(
        id = "",
        typeName = typeName,
        valueString = JsonApiTools.objectMapper.writeValueAsString(value)
    )

    constructor(
        type: BlobType,
        value: String
    ) : this(
        id = "",
        typeName = type.name.toLowerCase(),
        valueString = value
    )

    /**
     * @param value any object that will be stringified to JSON using [JsonApiTools.objectMapper]
     */
    constructor(
        type: BlobType,
        value: Any
    ) : this(
        id = "",
        typeName = type.name.toLowerCase(),
        valueString = JsonApiTools.objectMapper.writeValueAsString(value)
    )

    @get:JsonIgnore
    val type: BlobType
        get() = BlobType.fromName(typeName)

    /**
     * @return typed value deserialized from JSON [valueString] using [JsonApiTools.objectMapper]
     */
    @JsonIgnore
    fun <T> getValue(typeClass: Class<T>): T {
        return JsonApiTools.objectMapper.readValue(valueString, typeClass)
    }

    /**
     * @return typed value deserialized from JSON [valueString] using [JsonApiTools.objectMapper]
     *
     * @see jacksonTypeRef
     */
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