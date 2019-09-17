package org.tokend.sdk.api.integrations.marketplace

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.integrations.marketplace.model.MarketplaceBuyRequestAttributes
import org.tokend.sdk.api.integrations.marketplace.model.MarketplaceInvoiceAttributes
import org.tokend.sdk.api.integrations.marketplace.model.MarketplaceOfferResource
import retrofit2.Call
import retrofit2.http.*

interface MarketplaceService {
    @POST("integrations/marketplace/buy")
    @JvmSuppressWildcards
    fun submitBuyRequest(@Body body: DataEntity<AttributesEntity<MarketplaceBuyRequestAttributes>>):
            Call<DataEntity<AttributesEntity<MarketplaceInvoiceAttributes>>>

    @GET("integrations/marketplace/offers")
    @JvmSuppressWildcards
    fun getOffersPage(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<MarketplaceOfferResource>>>

    @GET("integrations/marketplace/offers/{id}")
    @JvmSuppressWildcards
    fun getOffer(@Path("id") id: String,
                 @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<MarketplaceOfferResource>>
}