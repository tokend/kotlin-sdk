package org.tokend.sdk.api.ingester.accounts

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.BaseResource
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.ingester.generated.resources.AccountResource
import org.tokend.sdk.api.ingester.generated.resources.SignerResource
import org.tokend.sdk.api.v3.base.JsonApiQueryParams
import org.tokend.sdk.api.v3.base.PageQueryParams

open class IngesterAccountsApi(
        protected val requests: CustomRequestsApi
) {
    @JvmOverloads
    open fun getById(id: String,
                     params: JsonApiQueryParams? = null): ApiRequest<AccountResource> {
        return requests.get(
                url = "accounts/$id",
                responseClass = AccountResource::class.java,
                queryMap = params.map()
        )
    }

    @JvmOverloads
    open fun getAccountSignersPage(accountId: String,
                                   params: PageQueryParams? = null): ApiRequest<DataPage<SignerResource>> {
        return requests.getPage(
                url = "accounts/$accountId/signers",
                pageItemClass = SignerResource::class.java,
                queryMap = params.map()
        )
    }
}