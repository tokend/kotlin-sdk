package org.tokend.sdk.api.v3.atomicswap

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.v3.model.generated.resources.AtomicSwapAskResource
import org.tokend.sdk.api.v3.atomicswap.params.AtomicSwapAskParams
import org.tokend.sdk.api.v3.atomicswap.params.AtomicSwapAsksPageParams

open class AtomicSwapsApi(
        protected val atomicSwapsService: AtomicSwapsService
) {
    @JvmOverloads
    open fun getAtomicSwapAsks(
            params: AtomicSwapAsksPageParams? = null
    ): ApiRequest<DataPage<AtomicSwapAskResource>> {
        return MappedRetrofitApiRequest(
                atomicSwapsService.getAtomicSwapAsks(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    @JvmOverloads
    open fun getAtomicSwapAskById(
            id: String,
            params: AtomicSwapAskParams? = null
    ): ApiRequest<AtomicSwapAskResource> {
        return MappedRetrofitApiRequest(
                atomicSwapsService.getAtomicSwapAskById(
                        id,
                        params.map()
                ),
                JSONAPIDocument<AtomicSwapAskResource>::get
        )
    }
}