package org.tokend.sdk.helpers.resourcegen.model

class OpenApi(
        val resourcesMap: Map<String, Resource>,
        val keysMap: Map<String, ResourceKey>,
        val innersMap: Map<String, InnerEntity>
)