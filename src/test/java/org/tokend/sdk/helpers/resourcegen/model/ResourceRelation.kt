package org.tokend.sdk.helpers.resourcegen.model

import com.fasterxml.jackson.databind.JsonNode
import org.tokend.sdk.helpers.resourcegen.YAML

class ResourceRelation(val name: String,
                       val description: String?,
                       val keyName: String,
                       val isArray: Boolean) {
    override fun toString(): String {
        return "Relation('$name')"
    }

    companion object {
        fun getFromComponentSchema(schema: JsonNode): List<ResourceRelation> {
            return schema["properties"]
                    .fields()
                    .asSequence()
                    .map { relationSchemaEntry ->
                        fromSchema(relationSchemaEntry.key, relationSchemaEntry.value)
                    }
                    .toList()
        }

        private fun fromSchema(name: String, schema: JsonNode): ResourceRelation {
            val dataSchema = schema["properties"]?.get("data") ?: schema

            val isArray = dataSchema["type"]?.asText() == "array"

            val keyName =
                    if (isArray)
                        YAML.getComponentReference(dataSchema["items"])!!
                    else
                        YAML.getComponentReference(dataSchema)!!

            return ResourceRelation(
                    name = name,
                    isArray = isArray,
                    keyName = keyName,
                    description = schema["description"]?.asText()
            )
        }
    }
}