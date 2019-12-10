package org.tokend.sdk.api.ingester.roles

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.BaseResource
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.ingester.generated.resources.RoleResource
import org.tokend.sdk.api.v3.base.JsonApiQueryParams
import org.tokend.sdk.api.v3.base.PageQueryParams

open class IngesterRolesApi(
        protected open val requests: CustomRequestsApi
) {
    @JvmOverloads
    open fun getPage(params: PageQueryParams? = null): ApiRequest<DataPage<RoleResource>> {
        return requests.getPage(
                url = "roles",
                pageItemClass = RoleResource::class.java,
                queryMap = params.map()
        )
    }

    @JvmOverloads
    open fun getById(id: String,
                     params: JsonApiQueryParams? = null): ApiRequest<RoleResource> {
        return requests.get(
                url = "roles/$id",
                responseClass = RoleResource::class.java,
                queryMap = params.map()
        )
    }
}