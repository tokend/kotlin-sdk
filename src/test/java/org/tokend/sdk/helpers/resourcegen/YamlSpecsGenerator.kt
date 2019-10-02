package org.tokend.sdk.helpers.resourcegen

import org.tokend.sdk.helpers.resourcegen.model.*

class YamlSpecsGenerator(openApi: OpenApi) {
    private val keysMap = openApi.keysMap.toMutableMap()
    private val resourcesMap = openApi.resourcesMap
    private val innersMap = openApi.innersMap

    private sealed class HierarchyEntry(var parent: HierarchyEntry?) : Iterable<HierarchyEntry> {
        override fun iterator(): Iterator<HierarchyEntry> {
            var current: HierarchyEntry = this
            return object : Iterator<HierarchyEntry> {
                override fun hasNext(): Boolean {
                    return current.parent != null
                }

                override fun next(): HierarchyEntry {
                    return current.also { current = current.parent!! }
                }
            }
        }

        class Key(val key: ResourceKey,
                  parent: HierarchyEntry?) : HierarchyEntry(parent) {
            override fun toString() = key.toString()
        }

        class Resource(val resource: org.tokend.sdk.helpers.resourcegen.model.Resource,
                       parent: HierarchyEntry?) : HierarchyEntry(parent) {
            override fun toString() = resource.toString()
        }
    }

    fun generate(): YamlSpecs {
        val resourcesHierarchy = getHierarchy()

        val resources =
                resourcesHierarchy
                        .map { hierarchyLine ->
                            hierarchyLine
                                    .filterIsInstance(HierarchyEntry.Resource::class.java)
                        }
                        .flatten()
                        .distinctBy { it.resource }
                        .map { res ->
                            res.resource.name.pascalToSnake() to generateResourceSpec(res)
                        }
                        .toMap()

        val inners = innersMap
                .map { (name, inner) ->
                    name.pascalToSnake() to generateInnerSpec(inner)
                }
                .toMap()

        return YamlSpecs(resources, inners)
    }

    private fun getHierarchy(): List<HierarchyEntry.Resource> {
        var hierarchy = resourcesMap.values.map { res ->
            val key = keysMap.getValue(res.keyName)
            HierarchyEntry.Resource(
                    res,
                    HierarchyEntry.Key(key, getKeyAggregationParent(key))
            )
        }

        transformAggregationKeysToResources(hierarchy)
        hierarchy = createResourcesForMultiTypeKeys(hierarchy)

        return hierarchy
    }

    // Transforms aggregation keys to synthetic base resources.
    private fun transformAggregationKeysToResources(hierarchy: Collection<HierarchyEntry.Resource>) {
        var changesOccurred = true

        while (changesOccurred) {
            changesOccurred = false

            hierarchy.forEach { hierarchyLine ->
                val itemEntryWithKeyParent = hierarchyLine.find { it.parent is HierarchyEntry.Key }
                val keyParentKeyParent = itemEntryWithKeyParent?.parent?.parent as? HierarchyEntry.Key

                if (keyParentKeyParent != null) {
                    // Add base resource instead of key.
                    val baseResourceKey = ResourceKey(
                            name = "Base" + keyParentKeyParent.key.name,
                            types = arrayOf(getSyntheticBaseKeyType(keyParentKeyParent.key.name)
                            ),
                            aggregatedKeys = emptyArray()
                    )
                    keysMap[baseResourceKey.name] = baseResourceKey

                    val baseResource = Resource(
                            name = baseResourceKey.name.substringBeforeLast("Key"),
                            keyName = baseResourceKey.name,
                            relationships = emptyList(),
                            attributes = emptyList()
                    )

                    itemEntryWithKeyParent.parent = HierarchyEntry.Resource(baseResource, keyParentKeyParent)

                    changesOccurred = true
                }
            }
        }
    }

    private fun getKeyAggregationParent(key: ResourceKey): HierarchyEntry.Key? {
        val parentKey = keysMap.values.find {
            it.isAggregation && it.aggregatedKeys.contains(key.name)
        } ?: return null

        return HierarchyEntry.Key(parentKey, getKeyAggregationParent(parentKey))
    }

    private fun createResourcesForMultiTypeKeys(hierarchy: Collection<HierarchyEntry.Resource>):
            List<HierarchyEntry.Resource> {
        val extendedHierarchy = mutableListOf<HierarchyEntry.Resource>()

        hierarchy.forEach { hierarchyLine ->
            val baseResource = hierarchyLine.resource
            val baseResourceKey = keysMap.getValue(baseResource.keyName)

            if (!baseResourceKey.isMultiType) {
                extendedHierarchy.add(hierarchyLine)
                return@forEach
            }

            baseResourceKey.types.forEach { keyType ->
                val name = keyType.snakeToPascal()

                val newKey = ResourceKey(
                        name = name + "Key",
                        types = arrayOf(keyType),
                        aggregatedKeys = emptyArray()
                )
                keysMap[newKey.name] = newKey

                val newResource = Resource(
                        name = name,
                        keyName = newKey.name,
                        attributes = emptyList(),
                        relationships = emptyList()
                )

                val newBaseResourceKey = ResourceKey(
                        name = baseResourceKey.name,
                        types = arrayOf(getSyntheticBaseKeyType(baseResourceKey.name)),
                        aggregatedKeys = emptyArray()
                )
                keysMap[newBaseResourceKey.name] = newBaseResourceKey

                val newBaseResource = Resource(
                        name = baseResource.name,
                        keyName = newBaseResourceKey.name,
                        relationships = baseResource.relationships,
                        attributes = baseResource.attributes
                )

                val newHierarchyLine = HierarchyEntry.Resource(
                        newResource,
                        HierarchyEntry.Resource(newBaseResource, hierarchyLine.parent)
                )

                extendedHierarchy.add(newHierarchyLine)
            }
        }

        return extendedHierarchy
    }

    private fun String.snakeToPascal(): String {
        return this
                .split('-')
                .joinToString("", transform = String::capitalize)
    }

    private fun String.pascalToSnake(): String {
        return this
                .decapitalize()
                .toCharArray()
                .joinToString("") {
                    if (it.isUpperCase())
                        "-${it.toLowerCase()}"
                    else
                        it.toString()
                }
    }

    private fun generateResourceSpec(resourceEntry: HierarchyEntry.Resource): String {
        val resource = resourceEntry.resource

        return generateResourceSpec(
                name = resource.name,
                key = getResourceKeyType(resource),
                base = (resourceEntry.parent as? HierarchyEntry.Resource)
                        ?.resource
                        ?.let(this::getResourceKeyType),
                attributes = resource.attributes,
                relationships = resource.relationships
        )
    }

    private fun generateResourceSpec(name: String,
                                     key: String,
                                     base: String?,
                                     attributes: List<Attribute>,
                                     relationships: List<ResourceRelation>): String {
        val attributesPart = generateAttributesSpecPart(attributes)

        val relationsPart =
                "relations:\n" +
                        relationships.joinToString("\n") { rel ->
                            """
                                |  -
                                |    name: ${rel.name}
                                |    resource: ${getKeyType(rel.keyName)}
                                |    is_collection: ${rel.isArray}
                                |    ${if (!rel.description.isNullOrBlank())
                                "description: " + formatMultilineString(rel.description.capitalize()) else ""}
                            """.trimMargin()
                        }

        return """
            |name: $name
            |key: $key
            |${if (base != null) "base: $base" else ""}
            |${if (attributes.isNotEmpty()) attributesPart else ""}
            |${if (relationships.isNotEmpty()) relationsPart else ""}
        """.trimMargin()
    }

    private fun getAttributeType(attribute: Attribute): String {
        val ref = attribute.reference
        val format = attribute.format
        val lowercaseFormat = format?.toLowerCase() ?: ""
        val type = attribute.type

        return when {
            ref == "Details" -> "Object"
            ref != null -> ref
            format == "Amount" -> "Amount"
            format == "date-time" -> "Date"
            lowercaseFormat.contains("uint32") -> "UInt32"
            lowercaseFormat.contains("uint64") -> "UInt64"
            lowercaseFormat.contains("int64") -> "Int64"
            lowercaseFormat.contains("int32")
                    || type == "number"
                    || type?.contains("int") == true -> "Int32"
            type == "boolean" -> "Bool"
            type == "string" -> "String"
            else -> throw IllegalStateException("Unknown type-format-reference combination:" +
                    " $type $format $ref")
        }
    }

    private fun getResourceKeyType(resource: Resource): String {
        return getKeyType(resource.keyName)
    }

    private fun getKeyType(keyName: String): String {
        return keysMap.getValue(keyName).types.firstOrNull()
                ?: getSyntheticBaseKeyType(keyName)
    }

    private fun getSyntheticBaseKeyType(keyName: String): String {
        return "base-" + keyName.substringBeforeLast("Key").pascalToSnake()
    }

    private fun formatMultilineString(content: String, depth: Int = 2): String {
        return "|-" + ("\n" + content).replace(
                Regex.fromLiteral("\n"),
                "\n" + (0 until ((depth + 1) * 2)).joinToString(separator = "", transform = { " " })
        )
    }

    private fun generateInnerSpec(inner: InnerEntity): String {
        return generateInnerSpec(inner.name, inner.attributes)
    }

    private fun generateInnerSpec(name: String,
                                  attributes: List<Attribute>): String {
        val attributesPart = generateAttributesSpecPart(attributes)

        return """
            |name: $name
            |${if (attributes.isNotEmpty()) attributesPart else ""}
        """.trimMargin()
    }

    private fun generateAttributesSpecPart(attributes: List<Attribute>): String {
        return "attributes:\n" +
                attributes.joinToString("\n") { attr ->
                    """
                                |  -
                                |    name: ${attr.name}
                                |    type: ${getAttributeType(attr)}
                                |    optional: ${attr.isNullable}
                                |    is_collection: ${attr.isArray}
                                |    ${if (!attr.description.isNullOrBlank())
                        "description: " + formatMultilineString(attr.description.capitalize()) else ""}
                            """.trimMargin()
                }
    }
}