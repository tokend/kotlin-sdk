package org.tokend.sdk.api.ingester.info

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.BaseResource
import org.tokend.sdk.api.custom.CustomRequestsApi

open class IngesterInfoApi(
        protected val requests: CustomRequestsApi
) {
    open fun get(): ApiRequest<BaseResource> {
        return requests.get(
                url = "info",
                responseClass = BaseResource::class.java
        )
    }
}