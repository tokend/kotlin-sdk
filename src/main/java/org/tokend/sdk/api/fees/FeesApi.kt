package org.tokend.sdk.api.fees

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.fees.params.FeeParams
import org.tokend.sdk.api.fees.model.Fee
import org.tokend.sdk.api.fees.model.Fees

open class FeesApi(
        protected val feesService: FeesService
) {

    /**
     *  Will return list of all possible fees for all assets.
     *  @see <a href="https://tokend.gitlab.io/docs/?http#get-fees">Docs</a>
     */
    open fun getFees(): ApiRequest<Fees> {
        return MappedRetrofitApiRequest(
                feesService.getFees(),
                { Fees(it.entries ?: emptyMap()) }
        )
    }

    /**
     *  Will return list of existing fees for all assets.
     *  @see <a href="https://tokend.gitlab.io/docs/#get-existing-fees">Docs</a>
     */
    open fun getExistingFees(): ApiRequest<Fees> {
        return MappedRetrofitApiRequest(
                feesService.getExistingFees(),
                { Fees(it.entries ?: emptyMap()) }
        )
    }

    /**
     * Will return specific fee information.
     * @see <a href="https://tokend.gitlab.io/docs/?http#get-fee-by-type">Docs</a>
     */
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