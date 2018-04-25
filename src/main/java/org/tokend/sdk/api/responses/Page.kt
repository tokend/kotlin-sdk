package org.tokend.sdk.api.responses


import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.util.*

class Page<T> constructor(private var type: TypeToken<Page<T>>? = object : TypeToken<Page<T>>() {})
    : Response() {

    @SerializedName("records")
    var records: ArrayList<T>? = null
        get() = if (field == null && this.embeddedPage != null) {
            this.embeddedPage.records
        } else field
    @SerializedName("links")
    private var links: Links? = null
    @SerializedName("_embedded")
    private val embeddedPage: EmbeddedPage<T>? = null
    @SerializedName("_links")
    private val embeddedLinks: Links? = null


    constructor(records: ArrayList<T>, links: Links, type: TypeToken<Page<T>>) : this(type) {
        this.records = records
        this.links = links
    }

    fun getLinks(): Links? {
        return this.links ?: this.embeddedLinks
    }

    private fun getType(): TypeToken<Page<T>>? {
        return this.type ?: object : TypeToken<Page<T>>() {}
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
