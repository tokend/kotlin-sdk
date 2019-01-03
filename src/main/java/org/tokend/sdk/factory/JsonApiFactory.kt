package org.tokend.sdk.factory

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.module.SimpleModule
import com.github.jasminb.jsonapi.ResourceConverter
import com.github.jasminb.jsonapi.retrofit.JSONAPIConverterFactory
import org.tokend.sdk.api.v2.accounts.model.AccountResource
import org.tokend.sdk.api.v2.assetpairs.model.AssetPairResource
import org.tokend.sdk.api.v2.assets.model.AssetResource
import org.tokend.sdk.api.v2.balances.model.BalanceResource
import org.tokend.sdk.api.v2.base.UnknownResource
import org.tokend.sdk.api.v2.fees.model.ExactFeeResource
import org.tokend.sdk.api.v2.fees.model.FeeResource
import org.tokend.sdk.api.v2.keyvalue.model.KeyValueEntryResource
import org.tokend.sdk.api.v2.kyc.model.KycResource
import org.tokend.sdk.api.v2.offers.model.OfferResource
import org.tokend.sdk.api.v2.requests.model.ReviewableRequestResource
import org.tokend.sdk.api.v2.requests.model.details.AssetCreateRequestDetailsResource
import org.tokend.sdk.api.v2.requests.model.details.ReviewableRequestDetailsResource
import org.tokend.sdk.api.v2.sales.model.SaleResource
import org.tokend.sdk.api.v2.signers.model.SignerResource
import org.tokend.sdk.api.v2.transactions.model.TransactionResource
import org.tokend.sdk.utils.ApiDateUtil
import org.tokend.sdk.utils.BigDecimalUtil
import java.math.BigDecimal
import java.util.*

/**
 * Constructs and provides base [ObjectMapper] and base [JSONAPIConverterFactory]
 * with type adapters for SDK models.
 */
class JsonApiFactory {
    fun getResourceConverter(): ResourceConverter {
        return resourceConverter
                ?: ResourceConverter(getObjectMapper(),
                        AccountResource::class.java,
                        AssetPairResource::class.java,
                        AssetResource::class.java,
                        BalanceResource::class.java,
                        FeeResource::class.java,
                        ExactFeeResource::class.java,
                        KeyValueEntryResource::class.java,
                        KycResource::class.java,
                        OfferResource::class.java,

                        ReviewableRequestResource::class.java,
                        ReviewableRequestDetailsResource::class.java,
                        AssetCreateRequestDetailsResource::class.java,

                        SaleResource::class.java,
                        SignerResource::class.java,
                        TransactionResource::class.java,
                        UnknownResource::class.java
                )
                        .also { resourceConverter = it }
    }

    fun getJsonApiConverterFactory(): JSONAPIConverterFactory {
        return jsonApiConverterFactory
                ?: JSONAPIConverterFactory(getResourceConverter())
                        .also { jsonApiConverterFactory = it }
    }

    fun getObjectMapper(): ObjectMapper {
        return objectMapper
                ?: createBaseObjectMapper()
                        .also { objectMapper = it }
    }

    private fun createBaseObjectMapper(): ObjectMapper {
        val module = SimpleModule()

        module.addSerializer(Date::class.java, getDateSerializer())
        module.addDeserializer(Date::class.java, getDateDeserializer())

        module.addSerializer(BigDecimal::class.java, getBigDecimalSerializer())
        module.addDeserializer(BigDecimal::class.java, getBigDecimalDeserializer())

        val mapper = ObjectMapper()
        mapper.registerModule(module)

        return mapper
    }

    private fun getDateSerializer(): JsonSerializer<Date> {
        return object : JsonSerializer<Date>() {
            override fun serialize(value: Date?,
                                   gen: JsonGenerator?,
                                   serializers: SerializerProvider?) {
                if (value == null) {
                    gen?.writeNull()
                } else {
                    gen?.writeString(ApiDateUtil.formatDateForRequest(value))
                }
            }
        }
    }

    private fun getDateDeserializer(): JsonDeserializer<Date> {
        return object : JsonDeserializer<Date>() {
            override fun deserialize(p: JsonParser?,
                                     ctxt: DeserializationContext?): Date? {
                val value = p?.valueAsString
                        ?: return null

                return ApiDateUtil.tryParseDate(value)
            }
        }
    }

    private fun getBigDecimalSerializer(): JsonSerializer<BigDecimal> {
        return object : JsonSerializer<BigDecimal>() {
            override fun serialize(value: BigDecimal?,
                                   gen: JsonGenerator?,
                                   serializers: SerializerProvider?) {
                if (value == null) {
                    gen?.writeNull()
                } else {
                    gen?.writeString(BigDecimalUtil.toPlainString(value))
                }
            }
        }
    }

    private fun getBigDecimalDeserializer(): JsonDeserializer<BigDecimal> {
        return object : JsonDeserializer<BigDecimal>() {
            override fun deserialize(p: JsonParser?,
                                     ctxt: DeserializationContext?): BigDecimal? {
                val value = p?.valueAsString
                        ?: return null

                return BigDecimalUtil.valueOf(value)
            }
        }
    }

    private companion object {
        private var resourceConverter: ResourceConverter? = null
        private var objectMapper: ObjectMapper? = null
        private var jsonApiConverterFactory: JSONAPIConverterFactory? = null
    }
}