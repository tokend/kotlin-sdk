package org.tokend.sdk.test.jsonapi

import com.github.jasminb.jsonapi.ResourceConverter
import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.v2.assets.model.AssetResource
import org.tokend.sdk.api.v2.base.UnknownResource
import org.tokend.sdk.api.v2.offers.model.OfferResource

class OffersModelTest {
    @Test
    fun singleOffer() {
        val converter = ResourceConverter(
                OfferResource::class.java,
                AssetResource::class.java,
                UnknownResource::class.java
        )

        val document = converter.readDocument(
                Data.offerResponseUnincluded.toByteArray(),
                OfferResource::class.java
        )

        val offer = document.get()

        JsonApiUtil.checkResourceNullability(offer)

        Assert.assertTrue(offer.hasAttributes())
        Assert.assertNotNull(offer.baseAsset)
        Assert.assertNotNull(offer.quoteAsset)
    }
}