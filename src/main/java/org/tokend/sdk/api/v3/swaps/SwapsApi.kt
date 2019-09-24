package org.tokend.sdk.api.v3.swaps

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.generated.resources.SwapResource
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
}