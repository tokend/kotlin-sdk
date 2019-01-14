package org.tokend.sdk.api.v2.operations

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.v2.operations.model.OperationResource
import org.tokend.sdk.api.v2.operations.params.OperationParamsV2
import org.tokend.sdk.api.v2.operations.params.OperationsPageParamsV2

open class OperationsApiV2(
        protected val operationsService: OperationsServiceV2
) {
    /**
     * @return operations list page
     */
    open fun get(params: OperationsPageParamsV2? = null): ApiRequest<DataPage<OperationResource>> {
        return MappedRetrofitApiRequest(
                operationsService.getOperations(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    open fun getById(id: String,
                     params: OperationParamsV2? = null): ApiRequest<OperationResource> {
        return MappedRetrofitApiRequest(
                operationsService.getOperationById(
                        id,
                        params.map()
                ),
                JSONAPIDocument<OperationResource>::get
        )
    }
}