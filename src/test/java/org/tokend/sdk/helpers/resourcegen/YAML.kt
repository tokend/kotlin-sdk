package org.tokend.sdk.helpers.resourcegen

import com.fasterxml.jackson.databind.JsonNode

typealias ComponentsMap = Map<String, JsonNode>

object YAML {
    const val REF = "\$ref"

    fun getReference(schema: JsonNode): String? {
        return schema[REF]?.asText()
    }

    fun getComponentReference(schema: JsonNode): String? {
        return getReference(schema)?.substringAfterLast('/')
    }
}