package org.tokend.sdk.test

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.base.params.PagingOrder
import org.tokend.sdk.api.base.params.PagingParams
import org.tokend.sdk.api.requests.params.RequestsParams

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
}