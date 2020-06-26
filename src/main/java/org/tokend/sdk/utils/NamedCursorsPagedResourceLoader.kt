package org.tokend.sdk.utils

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedCallableApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.model.DataPageWithNamedCursors

/***
 * Allow to get all specific joined collections paged data
 * with named cursors at once.
 */
abstract class NamedCursorsPagedResourceLoader<T> {

    protected var nextCursors: Map<String, String> = emptyMap()
    protected var noMoreItems: Boolean = false

    /***
     * Provides api request to get [DataPage] of specific resource.
     */
    abstract fun getPageRequest(nextCursors: Map<String, String>): ApiRequest<DataPageWithNamedCursors<T>>

    /***
     * @return request that will load all data page by page,
     * and then return it as list.
     */
    fun loadAll(): ApiRequest<List<T>> {

        return MappedCallableApiRequest(
                {
                    val result = mutableListOf<T>()

                    while (!noMoreItems) {
                        val page = getPageRequest(nextCursors).execute().get()
                        result.addAll(page.items)
                        nextCursors = page.nextCursors
                        noMoreItems = page.isLast
                    }

                    result.toList()
                },
                { it }
        )
    }
}