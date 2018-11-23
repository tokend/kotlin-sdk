package org.tokend.sdk.test

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.base.params.PagingOrder
import org.tokend.sdk.api.base.params.PagingParams
import org.tokend.sdk.api.requests.params.*

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

    @Test
    fun getIssuances() {
        val api = Util.getApi(
                url = "https://api.testnet.tokend.org"
        )

        val requests = api
                .requests
                .getIssuances(
                        IssuanceRequestsParams(
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
    fun getWithdrawals() {
        val api = Util.getApi(
                url = "https://api.testnet.tokend.org"
        )

        val requests = api
                .requests
                .getWithdrawals(
                        WithdrawalRequestsParams(
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
    fun getAmlAlerts() {
        val api = Util.getApi(
                url = "https://api.alice.tokend.io"
        )

        val requests = api
                .requests
                .getAmlAlerts(
                        AmlAlertRequestsParams(
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
    fun getLimitsUpdates() {
        val api = Util.getApi(
                url = "https://api.testnet.tokend.org"
        )

        val requests = api
                .requests
                .getLimitsUpdates(
                        LimitsUpdateRequestsParams(
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
    fun getKycUpdates() {
        val api = Util.getSignedApi()

        val requests = api
                .requests
                .getKycUpdates(
                        KycUpdateRequestsParams(
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