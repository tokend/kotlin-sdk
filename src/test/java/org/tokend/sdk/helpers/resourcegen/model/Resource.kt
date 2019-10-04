package org.tokend.sdk.helpers.resourcegen.model

import com.fasterxml.jackson.databind.JsonNode
import org.tokend.sdk.helpers.resourcegen.ComponentsMap
import org.tokend.sdk.helpers.resourcegen.YAML

class Resource(val name: String,
               val keyName: String,
               val attributes: List<Attribute>,
               val relationships: List<ResourceRelation>
) {
    constructor(name: String,
                schema: JsonNode,
                componentsMap: ComponentsMap) : this(
            name = name,
            keyName = YAML.getComponentReference(schema["properties"]["key"])!!,
            attributes = getAttributesComponentSchema(componentsMap, schema)
                    ?.let(Attribute.Companion::getFromComponentSchema)
                    ?: emptyList(),
            relationships = getRelationshipsComponentName(schema)
                    ?.let(componentsMap::get)
                    ?.let(ResourceRelation.Companion::getFromComponentSchema)
                    ?: emptyList()
    )

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return other is Resource && other.name == this.name
    }

    override fun toString(): String {
        return "Resource('$name')"
    }

    companion object {
        val SCHEMA_PREDICATE = { schema: JsonNode ->
            schema.has("properties")
                    && schema["properties"].has("key")
                    && schema["properties"]["key"].has(YAML.REF)
        }

        private fun getAttributesComponentSchema(componentsMap: ComponentsMap,
                                                 schema: JsonNode): JsonNode? {
            val attributesComponentName =
                    schema["properties"]?.get("attributes")?.let(YAML::getComponentReference)
                            ?: return null
            val attributesComponentSchema = componentsMap[attributesComponentName]
                    ?: return null

            val attributesReference = YAML.getComponentReference(attributesComponentSchema)

            return if (attributesReference != null)
                componentsMap[attributesReference]
            else
                attributesComponentSchema
        }

        private fun getRelationshipsComponentName(schema: JsonNode): String? {
            return schema["properties"]?.get("relationships")?.let(YAML::getComponentReference)
        }
    }
}