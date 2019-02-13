package org.tokend.sdk.api.v3.fees

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.v3.fees.model.ExactFeeResource
import org.tokend.sdk.api.v3.fees.model.FeeResource
import org.tokend.sdk.api.v3.fees.params.FeeCalculationParams
import org.tokend.sdk.api.v3.fees.params.FeesPageParamsV3

open class FeesApiV3(
        protected open val feesService: FeesServiceV3
) {
    open fun get(params: FeesPageParamsV3? = null): ApiRequest<DataPage<FeeResource>> {
        return MappedRetrofitApiRequest(
                feesService.getFees(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    open fun calculate(params: FeeCalculationParams): ApiRequest<ExactFeeResource> {
        return MappedRetrofitApiRequest(
                feesService.calculateFee(params.map()),
                JSONAPIDocument<ExactFeeResource>::get
        )
    }
}