package org.tokend.sdk.api.v2.fees

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.v2.fees.model.ExactFeeResource
import org.tokend.sdk.api.v2.fees.model.FeeResource
import org.tokend.sdk.api.v2.fees.params.FeeCalculationParams
import org.tokend.sdk.api.v2.fees.params.FeesPageParamsV2

open class FeesApiV2(
        protected open val feesService: FeesServiceV2
) {
    open fun get(params: FeesPageParamsV2? = null): ApiRequest<DataPage<FeeResource>> {
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