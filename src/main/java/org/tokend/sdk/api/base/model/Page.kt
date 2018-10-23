package org.tokend.sdk.api.base.model


import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Response with list of specific [T] items
 */
open class Page<T> {
    @SerializedName("records")
    var records: List<T> = emptyList()
        get() = (if (field.isEmpty() && this.embeddedPage != null) {
            this.embeddedPage.records
        } else field) ?: emptyList()
    @SerializedName("links")
    private var links: Links? = null
    @SerializedName("_embedded")
    private val embeddedPage: EmbeddedPage<T>? = null
    @SerializedName("_links")
    private val embeddedLinks: Links? = null

    fun getLinks(): Links? {
        return this.links ?: this.embeddedLinks
    }

    /**
     * Links connected to page response.
     */
    class Links internal constructor(@field:SerializedName("next")
                                     val next: Link?,
                                     @field:SerializedName("prev")
                                     val prev: Link?,
                                     @field:SerializedName("self")
                                     val self: Link?)

    private class EmbeddedPage<T>(@field:SerializedName("records")
                                  val records: ArrayList<T>?)
}
