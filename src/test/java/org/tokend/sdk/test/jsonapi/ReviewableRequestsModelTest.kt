package org.tokend.sdk.test.jsonapi

import com.github.jasminb.jsonapi.ResourceConverter
import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.v2.accounts.model.AccountResource
import org.tokend.sdk.api.v2.base.UnknownResource
import org.tokend.sdk.api.v2.requests.model.ReviewableRequestResource
import org.tokend.sdk.api.v2.requests.model.details.AssetCreateRequestDetailsResource

class ReviewableRequestsModelTest {
    @Test
    fun singleRequest() {
        val converter = ResourceConverter(
                ReviewableRequestResource::class.java,
                AssetCreateRequestDetailsResource::class.java,
                AccountResource::class.java,
                UnknownResource::class.java
        )

        val document = converter.readDocument(
                Data.requestAssetCreateResponse.toByteArray(),
                ReviewableRequestResource::class.java
        )

        val request = document.get()

        JsonApiUtil.checkResourceNullability(request)

        Assert.assertTrue(request.hasAttributes())
        Assert.assertTrue(request.getDetails<AssetCreateRequestDetailsResource>().hasAttributes())
    }
}