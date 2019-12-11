package org.tokend.sdk.api.ingester.publickeys

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.ingester.generated.resources.PublicKeyEntryResource

open class IngesterPublicKeysApi(
        protected open val requests: CustomRequestsApi
) {
    open fun getById(id: String): ApiRequest<PublicKeyEntryResource> {
        return requests.get(
                url = "horizon/public_keys/$id",
                responseClass = PublicKeyEntryResource::class.java
        )
    }
}