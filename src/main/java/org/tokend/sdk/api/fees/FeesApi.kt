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
     *
     *  @see org.tokend.sdk.api.v3.fees.FeesApiV3.get
     */
    @Deprecated("We are going to replace with FeesApiV3.get")
    open fun getFees(): ApiRequest<Fees> {
        return MappedRetrofitApiRequest(
                feesService.getFees(),
                { Fees(it.entries ?: emptyMap()) }
        )
    }

    /**
     *  Will return list of existing fees for all assets.
     *  @see <a href="https://tokend.gitlab.io/docs/#get-existing-fees">Docs</a>
     *
     *  @see org.tokend.sdk.api.v3.fees.FeesApiV3.get
     */
    @Deprecated("We are going to replace with FeesApiV3.get")
    open fun getExistingFees(accountId: String? = null): ApiRequest<Fees> {
        return MappedRetrofitApiRequest(
                feesService.getExistingFees(),
                { response ->
                    var entries = response.entries ?: emptyMap()
                    accountId?.let {
                        entries = entries.mapValues { entry ->
                            entry.value.filter { fee ->
                                fee.account_id == accountId ||
                                        fee.account_id.isEmpty()
                            }
                        }.filter { entry ->
                            entry.value.isNotEmpty()
                        }
                    }
                    Fees(entries)
                }
        )
    }

    /**
     * Will return specific fee information.
     * @see <a href="https://tokend.gitlab.io/docs/?http#get-fee-by-type">Docs</a>
     *
     *  @see org.tokend.sdk.api.v3.fees.FeesApiV3.getCalculatedFee
     */
    @Deprecated("We are going to replace with FeesApiV3.getCalculatedFee")
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