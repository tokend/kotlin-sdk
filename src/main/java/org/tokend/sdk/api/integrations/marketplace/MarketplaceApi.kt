package org.tokend.sdk.api.integrations.marketplace

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.integrations.marketplace.model.MarketplaceBuyRequestAttributes
import org.tokend.sdk.api.integrations.marketplace.model.MarketplaceInvoiceData
import org.tokend.sdk.api.integrations.marketplace.model.MarketplaceOfferPrice
import org.tokend.sdk.api.integrations.marketplace.model.MarketplaceOfferResource
import org.tokend.sdk.api.integrations.marketplace.params.MarketplaceOfferParams
import org.tokend.sdk.api.integrations.marketplace.params.MarketplaceOffersPageParams
import org.tokend.sdk.utils.BigDecimalUtil
import java.math.BigDecimal

open class MarketplaceApi(
        protected open val marketplaceService: MarketplaceService
) {
    open fun submitBuyRequest(request: MarketplaceBuyRequestAttributes): ApiRequest<MarketplaceInvoiceData> {
        return MappedRetrofitApiRequest(
                marketplaceService.submitBuyRequest(DataEntity(AttributesEntity(request))),
                { MarketplaceInvoiceData.fromInvoiceAttributes(it.data.attributes) }
        )
    }

    @JvmOverloads
    open fun getOffers(params: MarketplaceOffersPageParams? = null)
            : ApiRequest<DataPage<MarketplaceOfferResource>> {
        return MappedRetrofitApiRequest(
                marketplaceService.getOffersPage(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    @JvmOverloads
    open fun getOffer(id: String,
                      params: MarketplaceOfferParams? = null): ApiRequest<MarketplaceOfferResource> {
        return MappedRetrofitApiRequest(
                marketplaceService.getOffer(
                        id,
                        params.map()
                ),
                JSONAPIDocument<MarketplaceOfferResource>::get
        )
    }

    @JvmOverloads
    open fun getCalculatedOfferPrice(offerId: String,
                                     paymentMethodId: String,
                                     amount: BigDecimal,
                                     promocode: String? = null): ApiRequest<MarketplaceOfferPrice> {
        return MappedRetrofitApiRequest(
                marketplaceService.getCalculatedPrice(
                        offerId,
                        BigDecimalUtil.toPlainString(amount),
                        paymentMethodId,
                        promocode
                ),
                { it.data.attributes }
        )
    }
}