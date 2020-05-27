package org.tokend.sdk.api.integrations.cards

import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.base.model.NameValue
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.integrations.cards.model.CardState
import org.tokend.sdk.api.integrations.cards.model.CreateCardRequest
import org.tokend.sdk.api.integrations.cards.model.generated.resources.CardResource
import org.tokend.sdk.api.integrations.cards.params.CardsPageParams
import org.tokend.sdk.api.v3.base.JsonApiQueryParams

open class CardsApi(
        protected open val customRequestsApi: CustomRequestsApi
) {
    open fun createCard(request: CreateCardRequest) =
            customRequestsApi.post(
                    url = "integrations/cards",
                    body = DataEntity(request),
                    responseClass = CardResource::class.java
            )

    open fun getCards(params: CardsPageParams? = null) =
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

    open fun getCard(number: String,
                     params: JsonApiQueryParams? = null) = customRequestsApi.get(
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