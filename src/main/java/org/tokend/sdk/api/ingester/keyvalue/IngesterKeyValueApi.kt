package org.tokend.sdk.api.ingester.keyvalue

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.ingester.generated.resources.KeyValueEntryResource
import org.tokend.sdk.api.v3.base.PageQueryParams

open class IngesterKeyValueApi(
        protected val requests: CustomRequestsApi
) {
    @JvmOverloads
    open fun getPage(params: PageQueryParams? = null): ApiRequest<DataPage<KeyValueEntryResource>> {
        return requests.getPage(
                url = "horizon/key_values",
                pageItemClass = KeyValueEntryResource::class.java,
                queryMap = params.map()
        )
    }

    open fun getById(id: String): ApiRequest<KeyValueEntryResource> {
        return requests.get(
                url = "horizon/key_values/$id",
                responseClass = KeyValueEntryResource::class.java
        )
    }
}