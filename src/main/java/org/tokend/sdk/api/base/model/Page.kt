package org.tokend.sdk.api.base.model


import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

/**
 * Response with list of specific [T] items
 */
open class Page<T> {
    @JsonProperty("records")
    var records: List<T> = emptyList()
        get() = (if (field.isEmpty() && this.embeddedPage != null) {
            this.embeddedPage.records
        } else field) ?: emptyList()

    @JsonProperty("links")
    private var links: Links? = null

    @JsonProperty("_embedded")
    private val embeddedPage: EmbeddedPage<T>? = null

    @JsonProperty("_links")
    private val embeddedLinks: Links? = null

    fun getLinks(): Links? {
        return this.links ?: this.embeddedLinks
    }

    /**
     * Links connected to page response.
     */
    class Links internal constructor(
        @field:JsonProperty("next")
        val next: Link?,
        @field:JsonProperty("prev")
        val prev: Link?,
        @field:JsonProperty("self")
        val self: Link?
    )

    private class EmbeddedPage<T>(
        @field:JsonProperty("records")
        val records: ArrayList<T>?
    )
}
