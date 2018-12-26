package org.tokend.sdk.test.jsonapi

import com.github.jasminb.jsonapi.ResourceConverter
import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.v2.assetpairs.model.AssetPairResource
import org.tokend.sdk.api.v2.assets.model.AssetResource
import org.tokend.sdk.api.v2.base.UnknownResource
import org.tokend.wallet.xdr.AssetPairPolicy

class AssetPairsModelTest {
    @Test
    fun singlePair() {
        val converter = ResourceConverter(
                AssetPairResource::class.java,
                AssetResource::class.java,
                UnknownResource::class.java
        )

        val document = converter.readDocument(
                Data.assetPairResponse.toByteArray(),
                AssetPairResource::class.java
        )

        val pair = document.get()

        JsonApiUtil.checkResourceNullability(pair)

        Assert.assertTrue(pair.hasAttributes())
        Assert.assertNotNull(pair.baseAsset)
        Assert.assertNotNull(pair.quoteAsset)
        Assert.assertNotEquals(pair.baseAsset.id, pair.quoteAsset.id)
        Assert.assertTrue(pair.hasPolicy(AssetPairPolicy.TRADEABLE_SECONDARY_MARKET))
    }

    @Test
    fun pairsList() {
        val converter = ResourceConverter(
                AssetPairResource::class.java,
                AssetResource::class.java,
                UnknownResource::class.java
        )

        val document = converter.readDocumentCollection(
                Data.assetPairsListResponse.toByteArray(),
                AssetPairResource::class.java
        )

        Assert.assertNotNull(document.meta)
        Assert.assertNotNull(document.links)

        val pairs = document.get()

        JsonApiUtil.checkResourceNullability(pairs)

        Assert.assertTrue(pairs.isNotEmpty())

        val pair = pairs.first()

        Assert.assertTrue(pair.hasAttributes())
        Assert.assertNotNull(pair.baseAsset)
        Assert.assertNotNull(pair.quoteAsset)
        Assert.assertNotEquals(pair.baseAsset.id, pair.quoteAsset.id)
    }
}