package org.tokend.sdk.api.ingester.accounts

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.ingester.accounts.params.IngesterAccountParams
import org.tokend.sdk.api.ingester.accounts.params.IngesterAccountSignersPageParams
import org.tokend.sdk.api.ingester.generated.resources.AccountResource
import org.tokend.sdk.api.ingester.generated.resources.SignerResource

open class IngesterAccountsApi(
        protected val requests: CustomRequestsApi,
        protected val accountsService: IngesterAccountsService
) {
    @JvmOverloads
    open fun getById(id: String,
                     params: IngesterAccountParams? = null)
            : ApiRequest<AccountResource> {
        return requests.get(
                url = "horizon/accounts/$id",
                responseClass = AccountResource::class.java,
                queryMap = params.map()
        )
    }

    @JvmOverloads
    open fun getAccountSigners(accountId: String,
                               params: IngesterAccountSignersPageParams? = null)
            : ApiRequest<List<SignerResource>> {
        return MappedRetrofitApiRequest(
                accountsService.getAccountSigners(
                        accountId,
                        params.map()
                ),
                JSONAPIDocument<List<SignerResource>>::get
        )
    }
}