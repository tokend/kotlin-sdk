package org.tokend.sdk.utils

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.DataPage

/***
 * Simple representation of [PagedResourceLoader]
 *
 * @param distinct enables filter by distinct
 * based on [Object.equals] and [Object.hashCode] to avoid
 * duplicates on page-number-based pagination
 */
class SimplePagedResourceLoader<T>(
        private val requestProvider: (nextCursor: String?) -> ApiRequest<DataPage<T>>,
        distinct: Boolean = true
) : PagedResourceLoader<T>(distinct) {

    override fun getPageRequest(nextCursor: String?): ApiRequest<DataPage<T>> {
        return requestProvider.invoke(nextCursor)
    }
}