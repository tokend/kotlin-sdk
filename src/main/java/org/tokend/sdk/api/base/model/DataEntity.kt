package org.tokend.sdk.api.base.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.tokend.sdk.factory.JsonApiTools


/**
 * Represents request body with [T] data.
 */
open class DataEntity<out T>(
    @get:JsonProperty("data")
    val data: T
) {

    fun toJson() = JsonApiTools.objectMapper.writeValueAsString(data)
}
