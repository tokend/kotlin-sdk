package org.tokend.sdk.api.ingester.publickeys

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.BaseResource
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.ingester.generated.resources.PublicKeyEntryResource
import org.tokend.sdk.api.v3.base.JsonApiQueryParams

open class IngesterPublicKeysApi(
        protected open val requests: CustomRequestsApi
) {
    @JvmOverloads
    open fun getById(id: String,
                     params: JsonApiQueryParams? = null): ApiRequest<PublicKeyEntryResource> {
        return requests.get(
                url = "public_keys/$id",
                responseClass = PublicKeyEntryResource::class.java,
                queryMap = params.map()
        )
    }
}