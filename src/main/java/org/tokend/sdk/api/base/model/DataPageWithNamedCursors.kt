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
        val items: List<T>,
        val isLast: Boolean
) {
    /**
     * @return a copy of the [DataPageWithNamedCursors] with items transformed
     * by [transform]
     */
    inline fun <R> mapItems(transform: (T) -> R): DataPageWithNamedCursors<R> {
        return DataPageWithNamedCursors(
                nextCursors,
                items.map(transform),
                isLast
        )
    }

    /**
     * @return a copy of the [DataPageWithNamedCursors] with only the non-null results of
     * [transform] applied on it's items
     */
    inline fun <R : Any> mapItemsNotNull(transform: (T) -> R?): DataPageWithNamedCursors<R> {
        return DataPageWithNamedCursors(
                nextCursors,
                items.mapNotNull(transform),
                isLast
        )
    }

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

            val limit = DataPage.getNumberParamFromLink(selfLink, PagingParamsV2.QUERY_PARAM_LIMIT)
                    ?.toIntOrNull()
                    ?: 0

            val items = pageDocument.get()
            val isLast = nextLink == null || items.size < limit || limit == 0

            return DataPageWithNamedCursors(nextCursors, items, isLast)
        }
    }
}