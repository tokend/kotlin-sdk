package org.tokend.sdk.api.identity

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.identity.model.IdentityResource
import org.tokend.sdk.api.identity.params.IdentitiesPageParams

open class IdentitiesApi(
        protected open val identitesService: IdentitiesService
) {
    /**
     * @return identities list page
     */
    @JvmOverloads
    open fun get(params: IdentitiesPageParams? = null): ApiRequest<DataPage<IdentityResource>> {
        return MappedRetrofitApiRequest(
                identitesService.get(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }
}