package org.tokend.sdk.utils

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedCallableApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.PagingParamsHolder

abstract class PagedResourceLoader<T, R>
        where R : PagingParamsHolder {

    protected var nextCursor: String? = null
    protected var noMoreItems: Boolean = false

    protected abstract fun getNextPageParams(nextCursor: String?): R
    abstract fun getPageRequest(requestParams: R): ApiRequest<DataPage<T>>

    fun loadAll(): ApiRequest<List<T>> {

        return MappedCallableApiRequest(
                {
                    val result = arrayListOf<T>()
                    while (!noMoreItems) {
                        val page = getPageRequest(getNextPageParams(nextCursor)).execute().get()
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