package org.tokend.sdk.api.ingester.info

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.BaseResource
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.ingester.generated.resources.IngesterStateResource

open class IngesterInfoApi(
        protected val requests: CustomRequestsApi
) {
    open fun get(): ApiRequest<IngesterStateResource> {
        return requests.get(
                url = "horizon/info",
                responseClass = IngesterStateResource::class.java
        )
    }
}