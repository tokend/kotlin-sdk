package org.tokend.sdk.api.base.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.tokend.sdk.factory.JsonApiToolsProvider


/**
 * Represents request body with [T] data.
 */
open class DataEntity<out T>(
    @field:JsonProperty("data")
    val data: T
) {

    fun toJson() = JsonApiToolsProvider.getObjectMapper().writeValueAsString(data)
}
