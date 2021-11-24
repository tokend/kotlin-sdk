package org.tokend.sdk.utils.extentions

import com.fasterxml.jackson.databind.JsonNode
import org.tokend.sdk.api.base.model.BaseResource
import org.tokend.sdk.api.integrations.mergedhistory.model.generated.resources.EventResource
import org.tokend.sdk.factory.JsonApiTools

/**
 * Reads merged history event body as a particular resource of type [T]
 * assuming that it's attributes is 'data' and any other fields are includes.
 */
inline fun <reified T : BaseResource> EventResource.bodyToResource(): T =
        MergedHistoryEventResourceUtil.eventBodyToResource(body, T::class.java)

object MergedHistoryEventResourceUtil {
    @JvmStatic
    fun <T : BaseResource> eventBodyToResource(body: JsonNode,
                                               resourceClass: Class<out T>): T {
        val objectMapper = JsonApiTools.objectMapper
        val resourceNode = objectMapper.createObjectNode()
        resourceNode.set<JsonNode>("data", body["data"])
        resourceNode.set<JsonNode>(
                "included",
                objectMapper.createArrayNode().apply {
                    addAll(
                            body
                                    .fields()
                                    .asSequence()
                                    .mapNotNull { (bodyFieldName, bodyFieldValue) ->
                                        if (bodyFieldName != "data")
                                            bodyFieldValue
                                        else
                                            null
                                    }
                                    .toList()
                    )
                }
        )
        val resourceBody = objectMapper.writeValueAsBytes(resourceNode)
        return JsonApiTools.getResourceConverter()
                .readDocument(resourceBody, resourceClass)
                .get()
    }
}
