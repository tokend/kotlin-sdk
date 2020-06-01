package org.tokend.sdk.api.integrations.friends

import com.google.gson.reflect.TypeToken
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.integrations.friends.model.generated.resources.RecentPaymentResource
import org.tokend.sdk.api.integrations.friends.model.generated.resources.UserResource
import org.tokend.sdk.api.integrations.friends.params.RecentPaymentsPageParams

open class FriendsApi(
        protected open val customRequestsApi: CustomRequestsApi
) {
    open fun addFriends(identifiers: Collection<String>): ApiRequest<List<UserResource>> {
        return customRequestsApi.post(
                url = "integrations/friends",
                body = DataEntity(mapOf("relationships" to mapOf(
                        "identities" to DataEntity(identifiers.map {
                            "id" to it
                        })
                ))),
                responseType = object : TypeToken<List<UserResource>>() {}.type
        )
    }

    open fun getFriends(accountId: String,
                        pagingParamsV2: PagingParamsV2? = null): ApiRequest<DataPage<UserResource>> {
        return customRequestsApi.getPage(
                url = "integrations/friends/$accountId",
                queryMap = pagingParamsV2.map(),
                pageItemClass = UserResource::class.java
        )
    }

    open fun addRecentPayment(sourceAccountId: String,
                              destinationAccountId: String,
                              destinationCardNumber: String,
                              createdAtUnix: Long = System.currentTimeMillis() / 1000): ApiRequest<Void> {
        return customRequestsApi.post(
                url = "integrations/friends/recent_payments",
                body = DataEntity(mapOf(
                        "attributes" to mapOf("created_at" to createdAtUnix),
                        "relationships" to mapOf(
                                "source" to DataEntity(mapOf("id" to sourceAccountId)),
                                "destination" to DataEntity(mapOf("id" to destinationAccountId)),
                                "destination_card" to DataEntity(mapOf("id" to destinationCardNumber))
                        )
                )),
                responseClass = Void::class.java
        )
    }

    open fun getRecentPayments(sourceAccountId: String,
                               params: RecentPaymentsPageParams? = null): ApiRequest<DataPage<RecentPaymentResource>> {
        return customRequestsApi.getPage(
                url = "integrations/friends/recent_payments/$sourceAccountId",
                queryMap = params.map(),
                pageItemClass = RecentPaymentResource::class.java
        )
    }
}