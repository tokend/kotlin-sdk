package org.tokend.sdk.api.integrations.cards

import com.google.gson.reflect.TypeToken
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.base.model.NameValue
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.integrations.cards.model.CardState
import org.tokend.sdk.api.integrations.cards.model.CreateCardRequest
import org.tokend.sdk.api.integrations.cards.model.generated.resources.CardResource
import org.tokend.sdk.api.integrations.cards.params.CardParams
import org.tokend.sdk.api.integrations.cards.params.CardsPageParams

open class CardsApi(
        protected open val customRequestsApi: CustomRequestsApi
) {
    open fun createCard(request: CreateCardRequest) =
            customRequestsApi.post(
                    url = "integrations/cards",
                    body = mapOf(
                            "data" to request,
                            "included" to listOf(mapOf(
                                    "id" to "1",
                                    "type" to "cards-security-details",
                                    "relationships" to mapOf(
                                            "card" to DataEntity(mapOf(
                                                    "id" to request.number
                                            ))
                                    )
                            ))
                    ),
                    responseClass = CardResource::class.java
            )

    open fun getOwnCards(params: CardsPageParams? = null) =
            customRequestsApi.getPage(
                    url = "integrations/cards",
                    queryMap = params.map(),
                    pageItemClass = CardResource::class.java
            )

    open fun getForeignCards(params: CardsPageParams? = null) =
            customRequestsApi.getPage(
                    url = "integrations/cards/foreign",
                    queryMap = params.map(),
                    pageItemClass = CardResource::class.java
            )

    open fun getManyPublicCards(owners: Collection<String>): ApiRequest<List<CardResource>> =
            customRequestsApi.post(
                    url = "integrations/cards/public",
                    body = DataEntity(mapOf("relationships" to mapOf(
                            "owners" to DataEntity(owners.map { "id" to it })
                    ))),
                    responseType = object : TypeToken<List<CardResource>>() {}.type
            )

    open fun getCard(number: String,
                     params: CardParams? = null) = customRequestsApi.get(
            url = "integrations/cards/$number",
            queryMap = params.map(),
            responseClass = CardResource::class.java
    )

    open fun updateCardState(number: String,
                             newState: CardState) =
            customRequestsApi.patch(
                    url = "integrations/cards/$number",
                    body = DataEntity(AttributesEntity(mapOf(
                            "state" to NameValue(newState.name.toLowerCase(), newState.value)
                    ))),
                    responseClass = Void::class.java
            )

    open fun updateCardBalances(number: String,
                                bindBalances: Collection<String>,
                                unbindBalances: Collection<String>) =
            customRequestsApi.patch(
                    url = "integrations/cards/$number",
                    body = DataEntity(mapOf("relationships" to mapOf(
                            "bind_balances" to DataEntity(bindBalances.map {
                                mapOf("id" to it)
                            }),
                            "unbind_balances" to DataEntity(unbindBalances.map {
                                mapOf("id" to it)
                            })
                    ))),
                    responseClass = Void::class.java
            )

    open fun deleteCard(number: String) =
            customRequestsApi.delete(
                    url = "integrations/cards/$number",
                    responseClass = Void::class.java
            )
}