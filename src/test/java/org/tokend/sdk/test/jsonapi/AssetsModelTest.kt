package org.tokend.sdk.test.jsonapi

import com.github.jasminb.jsonapi.ResourceConverter
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.tokend.sdk.api.v2.assets.model.AssetResource
import org.tokend.sdk.api.v2.base.UnknownResource
import org.tokend.wallet.xdr.AssetPolicy

class AssetsModelTest {
    @Test
    fun singleAsset() {
        val converter = ResourceConverter(
                AssetResource::class.java,
                UnknownResource::class.java
        )

        val document = converter.readDocument(
                Data.assetResponse.toByteArray(),
                AssetResource::class.java
        )

        val asset = document.get()

        JsonApiUtil.checkResourceNullability(asset)

        assertTrue(asset.hasAttributes())
        assertNotNull(asset.logoLinks.related)
        assertNotNull(asset.termsLinks.related)
        assertNotNull(asset.details.name)
        assertTrue(asset.hasPolicy(AssetPolicy.TRANSFERABLE))
    }

    @Test
    fun assetsList() {
        val converter = ResourceConverter(
                AssetResource::class.java,
                UnknownResource::class.java
        )

        val document = converter.readDocumentCollection(
                Data.assetListResponse.toByteArray(),
                AssetResource::class.java
        )

        assertNotNull(document.meta)
        assertNotNull(document.links)

        val assets = document.get()

        JsonApiUtil.checkResourceNullability(assets)

        assertTrue(assets.isNotEmpty())

        val asset = assets.first()

        assertTrue(asset.hasAttributes())
        assertNotNull(asset.logoLinks.related)
        assertNotNull(asset.termsLinks.related)
        assertNotNull(asset.details.name)
        assertTrue(asset.hasPolicy(AssetPolicy.TRANSFERABLE))
    }
}