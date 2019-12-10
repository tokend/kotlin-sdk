package org.tokend.sdk.api.ingester.transactions

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.BaseResource
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.v3.base.JsonApiQueryParams
import org.tokend.sdk.api.v3.base.PageQueryParams
import org.tokend.sdk.api.v3.transactions.model.SubmitTransactionRequestBody

open class IngesterTransactionsApi(
        protected open val requests: CustomRequestsApi
) {
    open fun submit(envelopeBase64: String,
                    waitForIngest: Boolean): ApiRequest<BaseResource> {
        return requests.post(
                url = "transactions",
                body = SubmitTransactionRequestBody(envelopeBase64, waitForIngest),
                responseClass = BaseResource::class.java
        )
    }

    @JvmOverloads
    open fun getPage(params: PageQueryParams? = null): ApiRequest<DataPage<BaseResource>> {
        return requests.getPage(
                url = "transactions",
                pageItemClass = BaseResource::class.java,
                queryMap = params.map()
        )
    }

    @JvmOverloads
    open fun getById(id: String,
                     params: JsonApiQueryParams? = null): ApiRequest<BaseResource> {
        return requests.get(
                url = "transactions/$id",
                responseClass = BaseResource::class.java,
                queryMap = params.map()
        )
    }
}