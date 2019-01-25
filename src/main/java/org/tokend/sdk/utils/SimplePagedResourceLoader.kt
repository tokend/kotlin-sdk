package org.tokend.sdk.utils

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.DataPage

/***
 * Simple representation of [PagedResourceLoader]
 */
class SimplePagedResourceLoader<T>(
        private val requestProvider: (nextCursor: String?) -> ApiRequest<DataPage<T>>
) : PagedResourceLoader<T>() {

    override fun getPageRequest(nextCursor: String?): ApiRequest<DataPage<T>> {
        return requestProvider.invoke(nextCursor)
    }
}