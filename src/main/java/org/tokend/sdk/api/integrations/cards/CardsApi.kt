package org.tokend.sdk.api.integrations.cards

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.base.model.NameValue
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.integrations.cards.model.CardState
import org.tokend.sdk.api.integrations.cards.model.CreateCardRequest
import org.tokend.sdk.api.integrations.cards.model.generated.resources.CardResource
import org.tokend.sdk.api.integrations.cards.model.generated.resources.InfoResource
import org.tokend.sdk.api.integrations.cards.model.generated.resources.TransactionResource
import org.tokend.sdk.api.integrations.cards.params.CardParams
import org.tokend.sdk.api.integrations.cards.params.CardsPageParams
import org.tokend.sdk.factory.JsonApiToolsProvider

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

    open fun getManyPublicCards(owners: Collection<String>,
                                params: CardParams? = null): ApiRequest<List<CardResource>> =
            customRequestsApi.post(
                    url = "integrations/cards/public",
                    body = DataEntity(mapOf("relationships" to mapOf(
                            "owners" to DataEntity(owners.map { mapOf("id" to it) })
                    ))),
                    queryMap = params.map(),
                    responseClass = ByteArray::class.java
            )
                    .map {
                        JsonApiToolsProvider.getResourceConverter()
                                .readDocumentCollection(it, CardResource::class.java)
                                .get()
                    }

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

    open fun updateCardDetails(number: String,
                               newDetails: Any) =
            customRequestsApi.patch(
                    url = "integrations/cards/$number",
                    body = DataEntity(AttributesEntity(mapOf(
                            "details" to newDetails
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

    open fun getInfo() =
            customRequestsApi.get(
                    url = "integrations/cards/info",
                    responseClass = InfoResource::class.java
            )

    open fun approvePaymentTransaction(envelope: String,
                                       sourceCardNumber: String,
                                       destinationCardNumber: String) =
            customRequestsApi.post(
                    url = "integrations/cards/approve",
                    body = DataEntity(mapOf(
                            "attributes" to mapOf(
                                    "env" to envelope
                            ),
                            "relationships" to mapOf(
                                    "source_card" to DataEntity(mapOf("id" to sourceCardNumber)),
                                    "destination_card" to DataEntity(mapOf("id" to destinationCardNumber))
                            )
                    )),
                    responseClass = TransactionResource::class.java
            ).map(TransactionResource::getEnv)
}