package org.tokend.sdk.api.base.model

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Represents request body with [T] attributes.
 */
open class AttributesEntity<out T>(
    @get:JsonProperty("attributes")
    val attributes: T
)