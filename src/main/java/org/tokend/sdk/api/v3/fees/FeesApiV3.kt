package org.tokend.sdk.api.v3.fees

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.v3.model.generated.resources.CalculatedFeeResource
import org.tokend.sdk.api.v3.model.generated.resources.FeeRecordResource
import org.tokend.sdk.api.v3.fees.params.FeeCalculationParams
import org.tokend.sdk.api.v3.fees.params.FeesPageParamsV3

open class FeesApiV3(
        protected open val feesService: FeesServiceV3
) {
    open fun get(params: FeesPageParamsV3? = null): ApiRequest<DataPage<FeeRecordResource>> {
        return MappedRetrofitApiRequest(
                feesService.getFees(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    open fun getCalculatedFee(accountId: String, params: FeeCalculationParams): ApiRequest<CalculatedFeeResource> {
        return MappedRetrofitApiRequest(
                feesService.getCalculatedFee(accountId, params.map()),
                JSONAPIDocument<CalculatedFeeResource>::get
        )
    }
}