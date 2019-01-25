package org.tokend.sdk.utils

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedCallableApiRequest
import org.tokend.sdk.api.base.model.DataPage

/***
 * Allow to get all specific paged data at once.
 */
abstract class PagedResourceLoader<T> {

    protected var nextCursor: String? = null
    protected var noMoreItems: Boolean = false

    /***
     * Provides api request to get [DataPage] of specific resource.
     */
    abstract fun getPageRequest(nextCursor: String?): ApiRequest<DataPage<T>>

    /***
     * @return request that will load all data page by page,
     * and then return it as list.
     */
    fun loadAll(): ApiRequest<List<T>> {

        return MappedCallableApiRequest(
                {
                    val result = arrayListOf<T>()
                    while (!noMoreItems) {
                        val page = getPageRequest(nextCursor).execute().get()
                        result.addAll(page.items)
                        nextCursor = page.nextCursor
                        noMoreItems = page.isLast
                    }
                    result
                },
                { it }
        )
    }
}