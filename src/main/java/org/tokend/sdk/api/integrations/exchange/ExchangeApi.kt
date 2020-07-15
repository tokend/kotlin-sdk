package org.tokend.sdk.api.integrations.exchange

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.integrations.exchange.model.CreateExchangeRequest
import org.tokend.sdk.api.integrations.exchange.model.generated.resources.ConversionResource
import org.tokend.sdk.api.integrations.exchange.model.generated.resources.ExchangeRateResource

open class ExchangeApi(
        protected open val customRequestsApi: CustomRequestsApi
) {
    open fun getRate(baseAsset: String,
                     quoteAsset: String): ApiRequest<ExchangeRateResource> {
        return customRequestsApi.get(
                url = "integrations/exchange/rate",
                queryMap = mapOf(
                        "base" to baseAsset,
                        "quote" to quoteAsset
                ),
                responseClass = ExchangeRateResource::class.java
        )
    }

    open fun createExchange(request: CreateExchangeRequest): ApiRequest<ConversionResource> {
        return customRequestsApi.post(
                url = "integrations/exchange/convert",
                body = DataEntity(AttributesEntity(request)),
                responseClass = ConversionResource::class.java
        )
    }
}