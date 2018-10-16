package org.tokend.sdk.api.fees

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.fees.params.FeeParams
import org.tokend.sdk.api.fees.model.Fee

open class FeesApi(
        protected val feesService: FeesService
) {
    open fun getByType(feeType: Int,
                       params: FeeParams): ApiRequest<Fee> {
        return SimpleRetrofitApiRequest(
                feesService.getFee(
                        feeType,
                        params.map()
                )
        )
    }
}