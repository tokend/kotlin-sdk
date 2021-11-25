package org.tokend.sdk.api.v3.swaps

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.v3.model.generated.resources.SwapResource
import org.tokend.sdk.api.v3.swaps.params.SwapParams
import org.tokend.sdk.api.v3.swaps.params.SwapsPageParams

open class SwapsApi(
        protected open val swapsService: SwapsService
) {
    @JvmOverloads
    open fun get(params: SwapsPageParams? = null): ApiRequest<DataPage<SwapResource>> {
        return MappedRetrofitApiRequest(
                swapsService.getSwapsPage(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    @JvmOverloads
    open fun getById(id: String,
                     params: SwapParams? = null): ApiRequest<SwapResource> {
        return MappedRetrofitApiRequest(
                swapsService.getSwap(id, params.map()),
                JSONAPIDocument<SwapResource>::get
        )
    }
}