package org.tokend.sdk.api.v2.offers

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.generated.resources.OfferResource
import org.tokend.sdk.api.v2.offers.params.OfferParamsV2
import org.tokend.sdk.api.v2.offers.params.OffersPageParamsV2

open class OffersApiV2(
        protected val offersService: OffersServiceV2
) {
    /**
     * @return offers list page
     */
    open fun get(params: OffersPageParamsV2? = null): ApiRequest<DataPage<OfferResource>> {
        return MappedRetrofitApiRequest(
                offersService.getOffers(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    /**
     * @return offer by it's ID
     */
    open fun getById(id: String,
                     params: OfferParamsV2? = null): ApiRequest<OfferResource> {
        return MappedRetrofitApiRequest(
                offersService.getOfferById(
                        id,
                        params.map()
                ),
                JSONAPIDocument<OfferResource>::get
        )
    }
}