package org.tokend.sdk.api.ingester.rules

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.BaseResource
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.ingester.generated.resources.RuleResource
import org.tokend.sdk.api.v3.base.JsonApiQueryParams
import org.tokend.sdk.api.v3.base.PageQueryParams

open class IngesterRulesApi(
        protected open val requests: CustomRequestsApi
) {
    @JvmOverloads
    open fun getPage(params: PageQueryParams? = null): ApiRequest<DataPage<RuleResource>> {
        return requests.getPage(
                url = "horizon/rules",
                pageItemClass = RuleResource::class.java,
                queryMap = params.map()
        )
    }

    @JvmOverloads
    open fun getById(id: String,
                     params: JsonApiQueryParams? = null): ApiRequest<RuleResource> {
        return requests.get(
                url = "horizon/rules/$id",
                responseClass = RuleResource::class.java,
                queryMap = params.map()
        )
    }
}