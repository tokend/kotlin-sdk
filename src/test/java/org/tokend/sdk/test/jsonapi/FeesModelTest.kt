package org.tokend.sdk.test.jsonapi

import com.github.jasminb.jsonapi.ResourceConverter
import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.v2.assets.model.AssetResource
import org.tokend.sdk.api.v2.base.UnknownResource
import org.tokend.sdk.api.v2.fees.model.FeeResource

class FeesModelTest {
    @Test
    fun feesList() {
        val converter = ResourceConverter(
                FeeResource::class.java,
                AssetResource::class.java,
                UnknownResource::class.java
        )

        val document = converter.readDocumentCollection(
                Data.feesListResponse.toByteArray(),
                FeeResource::class.java
        )

        Assert.assertNotNull(document.meta)
        Assert.assertNotNull(document.links)

        val fees = document.get()

        Assert.assertTrue(fees.isNotEmpty())

        fees.forEach { fee ->
            Assert.assertTrue(fee.hasAttributes())
            // TODO: Uncomment when account model will be ready
            //Assert.assertEquals(fee.appliedTo.accountId, fee.account?.id)
            Assert.assertNotNull(fee.appliedTo)
        }
    }
}