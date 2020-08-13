package org.tokend.sdk.helpers.resourcegen

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import org.tokend.sdk.helpers.resourcegen.model.*
import java.io.File
import java.util.logging.Level
import java.util.logging.Logger

/**
 * Extracts resources, inners and keys from intermediate YAML
 * file created by TokenD OpenAPI generator.
 */
class OpenApiIntermediateParser {
    fun parse(fileName: String,
              ignoredKeys: Set<String> = emptySet()): OpenApi {
        val file = File(fileName)

        val componentsMap = ObjectMapper(YAMLFactory()).readTree(file)["components"]["schemas"]
                .fields()
                .asSequence()
                .map { it.toPair() }
                .toMap()

        val keysSchemaMap = componentsMap
                .filterKeys { ResourceKey.NAME_PREDICATE(it) && !ignoredKeys.contains(it) }

        val keysMap = keysSchemaMap
                .entries
                .map { keySchemaEntry ->
                    keySchemaEntry.key to ResourceKey(
                            name = keySchemaEntry.key,
                            schema = keySchemaEntry.value
                    )
                }
                .toMap()
                .toMutableMap()

        val resourceSchemaMap = componentsMap
                .filterValues(Resource.SCHEMA_PREDICATE)

        val resourcesMap = resourceSchemaMap
                .entries
                .mapNotNull { resourceSchemaEntry ->
                    val resource = Resource(
                            name = resourceSchemaEntry.key,
                            schema = resourceSchemaEntry.value,
                            componentsMap = componentsMap
                    )
                    if (ignoredKeys.contains(resource.keyName))
                        null
                    else
                        resourceSchemaEntry.key to resource
                }
                .toMap()
                .toMutableMap()

        val innersMap = componentsMap
                .filterKeys(InnerEntity.NAME_PREDICATE)
                .filterKeys {
                    !resourcesMap.keys.contains(it) && !keysMap.keys.contains(it)
                }
                .entries
                .map { innerSchemaEntry ->
                    innerSchemaEntry.key to InnerEntity(
                            name = innerSchemaEntry.key,
                            schema = innerSchemaEntry.value
                    )
                }
                .toMap()
                .toMutableMap()

        performPostProcessing(resourcesMap, keysMap, innersMap)

        // Unused inners are unwanted
        val usedInners = mutableSetOf<String>()
        resourcesMap.values.forEach { res ->
            markUsedInners(res.attributes, innersMap, usedInners)
        }
        innersMap.keys.toList().forEach { key ->
            if (!usedInners.contains(key)) {
                innersMap.remove(key)
            }
        }

        reportProblems(resourcesMap, keysMap, innersMap)

        return OpenApi(resourcesMap, keysMap, innersMap)
    }

    private fun markUsedInners(attributes: Collection<Attribute>,
                               innersMap: Map<String, InnerEntity>,
                               usedInners: MutableSet<String>) {
        attributes.forEach { attr ->
            val reference = attr.reference
            if (reference != null && innersMap.containsKey(reference)) {
                usedInners.add(reference)
                innersMap[reference]?.also { referencedInner ->
                    markUsedInners(referencedInner.attributes, innersMap, usedInners)
                }
            }
        }
    }

    private fun performPostProcessing(resourcesMap: MutableMap<String, Resource>,
                                      keysMap: MutableMap<String, ResourceKey>,
                                      innersMap: MutableMap<String, InnerEntity>) {
        // Custom transformations can be applied here.

        // Resolve 'asset' duplication in CreateAssetRequest.
        resourcesMap["CreateAssetRequest"]
                ?.attributes
                ?.find { it.name == "asset" }
                ?.also { assetAttribute ->
                    assetAttribute.name = "assetCode"
                }

        // KeyValueEntryValue tricky 'oneOf' workaround.
        innersMap["KeyValueEntryValue"]
                ?.attributes
                ?.forEach {
                    it.apply {
                        when (reference) {
                            "Str" -> {
                                type = "string"
                                reference = null
                            }
                            "U32" -> {
                                type = "uint32"
                                reference = null
                            }
                            "U64" -> {
                                type = "uint64"
                                reference = null
                            }
                        }
                    }
                }
    }

    private fun reportProblems(resourcesMap: Map<String, Resource>,
                               keysMap: Map<String, ResourceKey>,
                               innersMap: Map<String, InnerEntity>) {
        val logWarning = { warning: String ->
            Logger.getGlobal().log(Level.WARNING, warning)
        }

        // Aggregation keys can't be used for resources.
        resourcesMap
                .values
                .filter { keysMap.getValue(it.keyName).isAggregation }
                .apply {
                    forEach {
                        logWarning("'${it.name}' has key '${it.keyName}' which is the aggregation one")
                    }
                    if (isNotEmpty()) {
                        error("Aggregation keys can't be used for resources")
                    }
                }

        // Multitype keys can't be used for multiple resources.
        keysMap
                .values
                .map { key ->
                    key to resourcesMap.values.filter { res ->
                        res.keyName == key.name
                    }
                }
                .filter { (key, resources) ->
                    key.isMultiType && resources.size > 1
                }
                .apply {
                    forEach { (key, resources) ->
                        logWarning("Multitype key '${key.name}' is used by multiple resources: " +
                                resources.map(Resource::name).joinToString())
                    }
                    if (isNotEmpty()) {
                        error("Multitype keys can't be used for multiple resources")
                    }
                }

        // Name duplications in resource are not allowed
        resourcesMap
                .values
                .map { res ->
                    res to listOf(
                            res.attributes.map { attr ->
                                attr.name
                            },
                            res.relationships.map { rel ->
                                rel.name
                            }
                    ).flatten()
                }
                .mapNotNull { (res, names) ->
                    val duplicatedNames = names
                            .groupBy { it }
                            .mapValues { (_, list) ->
                                list.size
                            }
                            .filterValues { it > 1 }

                    if (duplicatedNames.isNotEmpty()) {
                        res to duplicatedNames
                    } else {
                        null
                    }
                }
                .apply {
                    forEach { (res, names) ->
                        logWarning("Resource '${res.name}' has duplicated names: " +
                                names.keys.joinToString(""))
                    }
                    if (isNotEmpty()) {
                        error("Name duplications in resource are not allowed")
                    }
                }

        val attributesWithParentNames =
                listOf(
                        resourcesMap.values.map { res ->
                            res.attributes.map { attr ->
                                attr to res.name
                            }
                        }.flatten(),

                        innersMap.values.map { inn ->
                            inn.attributes.map { attr ->
                                attr to inn.name
                            }
                        }.flatten()
                ).flatten()

        // Unknown type attributes are suspicious
        attributesWithParentNames
                .filter { (attr, _) ->
                    attr.type == null && attr.reference == null
                }
                .forEach { (attr, parent) ->
                    logWarning("Attribute '${attr.name}' of '$parent' has no type and no ref")
                }

        // Usually XDR-format attributes are Enum or Mask
        attributesWithParentNames
                .filter { (attr, _) ->
                    attr.format?.startsWith("xdr.") == true
                            && attr.reference == null
                }
                .forEach { (attr, parent) ->
                    logWarning("Attribute '${attr.name}' of '$parent' is XDR value but has no ref")
                }

        // Empty resources are suspicious
        resourcesMap.values
                .filter { it.attributes.isEmpty() && it.relationships.isEmpty() }
                .forEach {
                    logWarning("Empty resource '${it.name}'")
                }
    }
}