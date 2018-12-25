package org.tokend.sdk.test.jsonapi

import com.github.jasminb.jsonapi.ResourceConverter
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.tokend.sdk.api.v2.assets.model.AssetResource
import org.tokend.sdk.api.v2.base.UnknownResource

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

        assertTrue(asset.hasAttributes())
        assertNotNull(asset.logoLinks.related)
        assertNotNull(asset.termsLinks.related)
        assertNotNull(asset.details.name)
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

        assertTrue(assets.isNotEmpty())

        val asset = assets.first()

        assertTrue(asset.hasAttributes())
        assertNotNull(asset.logoLinks.related)
        assertNotNull(asset.termsLinks.related)
        assertNotNull(asset.details.name)
    }
}