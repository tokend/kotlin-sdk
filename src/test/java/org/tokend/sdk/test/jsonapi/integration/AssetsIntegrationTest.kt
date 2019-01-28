package org.tokend.sdk.test.jsonapi.integration

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v2.assets.params.AssetParams
import org.tokend.sdk.api.v2.assets.params.AssetsPageParams
import org.tokend.sdk.test.Util
import org.tokend.sdk.test.jsonapi.JsonApiUtil
import org.tokend.wallet.xdr.AssetPolicy

class AssetsIntegrationTest {
    @Test
    fun assetsList() {
        val api = Util.getApi()

        val assets = api.v2
                .assets
                .get(
                        AssetsPageParams(
                                pagingParams = PagingParamsV2(
                                        limit = 5
                                )
                        )
                )
                .execute()
                .get()

        Assert.assertTrue("No assets found", assets.items.isNotEmpty())
        JsonApiUtil.checkResourceNullability(assets.items)
    }

    @Test
    fun assetsListWithIncludes() {
        val api = Util.getApi()

        val assets = api.v2
                .assets
                .get(
                        AssetsPageParams(
                                pagingParams = PagingParamsV2(
                                        limit = 5
                                ),
                                include = listOf(AssetParams.Includes.OWNER)
                        )
                )
                .execute()
                .get()

        Assert.assertTrue("No assets found", assets.items.isNotEmpty())
        JsonApiUtil.checkResourceNullability(assets.items)
    }

    @Test
    fun assetsListWithPolicyFilter() {
        val api = Util.getApi()
        val policy = AssetPolicy.STATS_QUOTE_ASSET

        val assets = api.v2
                .assets
                .get(
                        AssetsPageParams.Builder()
                                .withPolicies(policy)
                                .withInclude(AssetParams.Includes.OWNER)
                                .build()
                )
                .execute()
                .get()

        Assert.assertTrue("No assets found", assets.items.isNotEmpty())
        Assert.assertTrue("Not all assets have the requested policy $policy",
                assets.items.all { it.hasPolicy(policy) })
        JsonApiUtil.checkResourceNullability(assets.items)
    }

    @Test
    fun singleAsset() {
        val api = Util.getApi()

        val assets = api.v2
                .assets
                .get(
                        AssetsPageParams.Builder()
                                .withPagingParams(PagingParamsV2(limit = 1))
                                .build()
                )
                .execute()
                .get()

        val id = assets.items.first().id

        val asset = api.v2
                .assets
                .getById(id, AssetParams(
                        include = listOf(AssetParams.Includes.OWNER))
                )
                .execute()
                .get()

        JsonApiUtil.checkResourceNullability(asset)
    }
}