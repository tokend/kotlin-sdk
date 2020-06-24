package org.tokend.sdk.api.base.model

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.base.params.PagingParamsWithNamedCursors
import java.net.URLDecoder

/**
 * Data page for joined collection with multiple named cursors.
 *
 * @see nextCursors
 * @see PagingParamsWithNamedCursors
 */
class DataPageWithNamedCursors<T>(
        val nextCursors: Map<String, String>,
        items: List<T>,
        isLast: Boolean
) : DataPage<T>(null, items, isLast) {
    override val nextCursor: String?
        get() = throw IllegalStateException("You can't use DataPageWithNamedCursors for regular pagination")

    companion object {
        /**
         * Creates [DataPageWithNamedCursors] from collection [JSONAPIDocument] with specific meta info.
         */
        fun <T> fromPageDocument(pageDocument: JSONAPIDocument<List<T>>): DataPageWithNamedCursors<T> {
            val selfLink = URLDecoder.decode(pageDocument.links.self.href, "UTF-8")
            val nextLink = pageDocument.links?.next?.href
                    ?.takeIf(String::isNotBlank)
                    ?.let { URLDecoder.decode(it, "UTF-8") }

            val nextCursors =
                    if (nextLink == null)
                        emptyMap()
                    else
                        PagingParamsWithNamedCursors.getCursorsFromUrl(nextLink)

            val limit = getNumberParamFromLink(selfLink, PagingParamsV2.QUERY_PARAM_LIMIT)
                    ?.toIntOrNull()
                    ?: 0

            val items = pageDocument.get()
            val isLast = nextLink == null || items.size < limit || limit == 0

            return DataPageWithNamedCursors(nextCursors, items, isLast)
        }
    }
}