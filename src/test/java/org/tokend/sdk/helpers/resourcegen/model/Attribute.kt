package org.tokend.sdk.helpers.resourcegen.model

import com.fasterxml.jackson.databind.JsonNode
import org.tokend.sdk.helpers.resourcegen.YAML

class Attribute(val name: String,
                val type: String?,
                val format: String?,
                val description: String?,
                val reference: String?,
                val isNullable: Boolean,
                val isArray: Boolean) {
    private class TypeAttributes(
            val type: String?,
            val format: String?,
            val reference: String?
    )

    private constructor(name: String,
                        description: String?,
                        isNullable: Boolean,
                        isArray: Boolean,
                        typeAttributes: TypeAttributes) : this(
            name = name,
            type = typeAttributes.type,
            format = typeAttributes.format,
            description = description,
            reference = typeAttributes.reference,
            isNullable = isNullable,
            isArray = isArray
    )

    companion object {
        fun getFromComponentSchema(schema: JsonNode): List<Attribute> {
            val attributeEntries =
                    schema["properties"]
                            ?.fields()
                            ?.asSequence()
                            ?.toList()
                            ?: emptyList()

            return attributeEntries.map { attributeEntry ->
                fromSchema(
                        name = attributeEntry.key,
                        schema = attributeEntry.value
                )
            }
        }

        private fun fromSchema(name: String, schema: JsonNode): Attribute {
            val isArray = schema["type"]?.asText() == "array"

            val typeAttributes =
                    if (isArray)
                        getTypeAttributes(schema["items"])
                    else
                        getTypeAttributes(schema)

            return Attribute(
                    name = name,
                    description = schema["description"]?.asText(),
                    isNullable = schema["allOf"]
                            ?.any { it["nullable"]?.asBoolean() == true }
                            ?: schema["nullable"]?.asBoolean() ?: false,
                    isArray = isArray,
                    typeAttributes = typeAttributes
            )
        }

        private fun getTypeAttributes(schema: JsonNode): TypeAttributes {
            return TypeAttributes(
                    type = schema["type"]?.asText(),
                    format = schema["format"]?.asText(),
                    reference = YAML.getComponentReference(schema)
                            ?: schema["allOf"]?.get(0)?.let(YAML::getComponentReference)
            )
        }
    }

    override fun toString(): String {
        return "Attribute('$name')"
    }
}