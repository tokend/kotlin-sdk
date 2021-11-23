package org.tokend.sdk.api.integrations.friends

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
    open fun addFriends(selfAccountId: String,
                        identifiers: Collection<String>): ApiRequest<Void> {
        return customRequestsApi.post(
                url = "integrations/friends/$selfAccountId/multi",
                body = DataEntity(mapOf("relationships" to mapOf(
                        "new_friends" to DataEntity(identifiers.map {
                            mapOf("id" to it)
                        })
                ))),
                responseClass = Void::class.java
        )
    }

    open fun addFriend(selfAccountId: String,
                       friendAccountId: String): ApiRequest<Void> {
        return customRequestsApi.post(
                url = "integrations/friends/$selfAccountId",
                body = DataEntity(mapOf("relationships" to mapOf(
                        "new_friend" to DataEntity(mapOf("id" to friendAccountId))
                ))),
                responseClass = Void::class.java
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
                url = "integrations/friends/$sourceAccountId/recent_payments",
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
                url = "integrations/friends/$sourceAccountId/recent_payments",
                queryMap = params.map(),
                pageItemClass = RecentPaymentResource::class.java
        )
    }
}