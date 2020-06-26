package org.tokend.sdk.utils

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.DataPageWithNamedCursors

/***
 * Simple representation of [NamedCursorsPagedResourceLoader]
 */
class SimpleNamedCursorsPagedResourceLoader<T>(
        private val requestProvider: (nextCursors: Map<String, String>) -> ApiRequest<DataPageWithNamedCursors<T>>
): NamedCursorsPagedResourceLoader<T>() {

    override fun getPageRequest(nextCursors: Map<String, String>): ApiRequest<DataPageWithNamedCursors<T>> {
        return requestProvider.invoke(nextCursors)
    }
}