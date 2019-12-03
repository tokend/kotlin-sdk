package org.tokend.sdk.helpers.resourcegen.model

import com.fasterxml.jackson.databind.JsonNode
import org.tokend.sdk.helpers.resourcegen.YAML

class Attribute(var name: String,
                var type: String?,
                val format: String?,
                val description: String?,
                var reference: String?,
                val isNullable: Boolean,
                val isArray: Boolean,
                val isStringMap: Boolean) {
    private class TypeAttributes(
            val type: String?,
            val format: String?,
            val reference: String?
    )

    private constructor(name: String,
                        description: String?,
                        isNullable: Boolean,
                        isArray: Boolean,
                        isStringMap: Boolean,
                        typeAttributes: TypeAttributes) : this(
            name = name,
            type = typeAttributes.type,
            format = typeAttributes.format,
            description = description,
            reference = typeAttributes.reference,
            isNullable = isNullable,
            isArray = isArray,
            isStringMap = isStringMap
    )

    var jsonName: String = name

    companion object {
        fun getFromComponentSchema(schema: JsonNode): List<Attribute> {
            val required = schema["required"]
                    ?.map { it.asText() }
                    ?.toHashSet()
                    ?: emptySet<String>()

            val attributeEntries =
                    schema["properties"]
                            ?.fields()
                            ?.asSequence()
                            ?.toList()
                            ?: emptyList()

            return attributeEntries.map { attributeEntry ->
                fromSchema(
                        name = attributeEntry.key,
                        schema = attributeEntry.value,
                        required = required
                )
            }
        }

        private fun fromSchema(name: String, schema: JsonNode,
                               required: Collection<String>): Attribute {
            val isArray = schema["type"]?.asText() == "array"

            val additionalProperties = schema["additionalProperties"]
            val isStringMap = additionalProperties != null

            val typeAttributes =
                    if (isArray)
                        getTypeAttributes(schema["items"])
                    else if (isStringMap) {
                        val obtainedTypeAttributes = getTypeAttributes(additionalProperties)
                        if (obtainedTypeAttributes.type != null || obtainedTypeAttributes.reference != null)
                            obtainedTypeAttributes
                        else
                            TypeAttributes(
                                    type = null,
                                    format = null,
                                    reference = "Details"
                            )
                    } else
                        getTypeAttributes(schema)

            return Attribute(
                    name = name,
                    description = schema["description"]?.asText(),
                    isNullable = !required.contains(name),
                    isArray = isArray,
                    isStringMap = isStringMap,
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