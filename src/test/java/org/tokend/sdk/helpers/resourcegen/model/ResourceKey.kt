package org.tokend.sdk.helpers.resourcegen.model

import com.fasterxml.jackson.databind.JsonNode
import org.tokend.sdk.helpers.resourcegen.YAML

class ResourceKey(val name: String,
                  val types: Array<String>,
                  val aggregatedKeys: Array<String>) {
    constructor(name: String,
                schema: JsonNode) : this(
            name = name,
            types = getTypes(schema),
            aggregatedKeys = getAggregatedKeys(schema)
    )

    val isMultiType = types.size > 1
    val isAggregation = aggregatedKeys.isNotEmpty()

    override fun equals(other: Any?): Boolean {
        return other is ResourceKey && other.name == this.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun toString(): String {
        return "Key('$name')"
    }

    companion object {
        val NAME_PREDICATE = { name: String ->
            name.endsWith("Key")
        }

        private fun getTypes(schema: JsonNode): Array<String> {
            val propertiesSchema = schema["properties"]
            val typeSchema = propertiesSchema["type"]

            return if (typeSchema.has("enum")) {
                // Enum type type key.
                typeSchema["enum"]
                        .map(JsonNode::asText)
                        .toTypedArray()
            } else {
                // Composite key.
                emptyArray()
            }
        }

        private fun getAggregatedKeys(schema: JsonNode): Array<String> {
            return schema["properties"]
                    .fields()
                    .asSequence()
                    .toList()
                    .filter { it.key.endsWith("_key") }
                    .mapNotNull {
                        YAML.getComponentReference(it.value)
                    }
                    .toTypedArray()
        }
    }
}