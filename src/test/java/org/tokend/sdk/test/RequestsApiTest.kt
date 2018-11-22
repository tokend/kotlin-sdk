package org.tokend.sdk.test

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.base.params.PagingOrder
import org.tokend.sdk.api.base.params.PagingParams
import org.tokend.sdk.api.requests.params.AssetRequestsParams
import org.tokend.sdk.api.requests.params.PreIssuanceRequestsParams
import org.tokend.sdk.api.requests.params.RequestsParams
import org.tokend.sdk.api.requests.params.SaleRequestsParams

class RequestsApiTest {
    @Test
    fun getAll() {
        val api = Util.getApi()

        val requests = api
                .requests
                .getAll(
                        RequestsParams(
                                pagingParams = PagingParams(
                                        order = PagingOrder.DESC
                                )
                        )
                )
                .execute()
                .get()
                .items

        Assert.assertTrue(requests.isNotEmpty())
        Util.checkNullabilityViolations(requests)
    }

    @Test
    fun getAssets() {
        val api = Util.getApi()

        val requests = api
                .requests
                .getAssets(
                        AssetRequestsParams(
                                pagingParams = PagingParams(
                                        order = PagingOrder.DESC
                                )
                        )
                )
                .execute()
                .get()
                .items

        Assert.assertTrue(requests.isNotEmpty())
        Util.checkNullabilityViolations(requests)
    }

    @Test
    fun getSales() {
        val api = Util.getApi()

        val requests = api
                .requests
                .getSales(
                        SaleRequestsParams(
                                pagingParams = PagingParams(
                                        order = PagingOrder.DESC
                                )
                        )
                )
                .execute()
                .get()
                .items

        Assert.assertTrue(requests.isNotEmpty())
        Util.checkNullabilityViolations(requests)
    }

    @Test
    fun getPreIssuances() {
        val api = Util.getApi(
                url = "https://api.testnet.tokend.org"
        )

        val requests = api
                .requests
                .getPreIssuances(
                        PreIssuanceRequestsParams(
                                pagingParams = PagingParams(
                                        order = PagingOrder.DESC
                                )
                        )
                )
                .execute()
                .get()
                .items

        Assert.assertTrue(requests.isNotEmpty())
        Util.checkNullabilityViolations(requests)
    }
}