package org.tokend.sdk.api.v3.offers

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.v3.model.generated.resources.OfferResource
import org.tokend.sdk.api.v3.offers.params.OfferParamsV3
import org.tokend.sdk.api.v3.offers.params.OffersPageParamsV3

open class OffersApiV3(
        protected val offersService: OffersServiceV3
) {
    /**
     * @return offers list page
     */
    open fun get(params: OffersPageParamsV3? = null): ApiRequest<DataPage<OfferResource>> {
        return MappedRetrofitApiRequest(
                offersService.getOffers(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    /**
     * @return offer by it's ID
     */
    open fun getById(id: String,
                     params: OfferParamsV3? = null): ApiRequest<OfferResource> {
        return MappedRetrofitApiRequest(
                offersService.getOfferById(
                        id,
                        params.map()
                ),
                JSONAPIDocument<OfferResource>::get
        )
    }
}