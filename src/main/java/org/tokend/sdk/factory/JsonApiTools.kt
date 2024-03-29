package org.tokend.sdk.factory

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.jasminb.jsonapi.ResourceConverter
import com.github.jasminb.jsonapi.retrofit.JSONAPIConverterFactory
import org.jetbrains.annotations.TestOnly
import org.tokend.sdk.api.v3.model.generated.resources.AllResources
import org.tokend.sdk.api.identity.model.IdentityResource
import org.tokend.sdk.api.identity.model.IdentitySettingsResource
import org.tokend.sdk.api.integrations.booking.model.scheduler.SchedulerPayloadResource
import org.tokend.sdk.api.integrations.marketplace.model.MarketplaceOfferResource
import org.tokend.sdk.api.integrations.marketplace.model.MarketplacePaymentMethodResource
import org.tokend.sdk.factory.JsonApiTools.addExtraResources
import org.tokend.sdk.factory.JsonApiTools.objectMapper
import org.tokend.sdk.utils.ApiDateUtil
import org.tokend.sdk.utils.BigDecimalUtil
import retrofit2.Converter
import retrofit2.converter.jackson.JacksonConverterFactory
import java.math.BigDecimal
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger

/**
 * Constructs and provides base [ObjectMapper] and base [JSONAPIConverterFactory]
 * with type adapters for SDK models.
 *
 * @see objectMapper
 * @see addExtraResources
 */
object JsonApiTools {
    private val extraResources = mutableSetOf<Class<*>>(
        IdentityResource::class.java,
        MarketplaceOfferResource::class.java,
        MarketplacePaymentMethodResource::class.java,
        IdentitySettingsResource::class.java,
        SchedulerPayloadResource::class.java
    )

    /**
     * Adds extra JSONAPI resource out of [AllResources]
     * to use in [ResourceConverter].
     *
     * Make sure to add all extra resources before
     * the first call of [getResourceConverter]
     */
    @JvmStatic
    fun addExtraResources(vararg resourceClass: Class<*>) {
        if (mResourceConverter != null) {
            Logger.getGlobal().log(
                Level.WARNING, "Attempt to add extra resources " +
                        "when the converter is already created and cached. This will have no effect!"
            )
        }
        extraResources.addAll(resourceClass)
    }

    /**
     * @return [ResourceConverter] set up for TokenD JSON API.
     *
     * Notice that the first call may cause a delay
     */
    @JvmStatic
    fun getResourceConverter(): ResourceConverter = synchronized(this) {
        return mResourceConverter
            ?: ResourceConverter(
                objectMapper,
                *AllResources.ARRAY,
                *org.tokend.sdk.api.integrations.booking.model.generated.resources.AllResources.ARRAY,
                *org.tokend.sdk.api.integrations.booking.model.scheduler.generated.resources.AllResources.ARRAY,
                *org.tokend.sdk.api.integrations.kycprovider.model.generated.resources.AllResources.ARRAY,
                *extraResources.toTypedArray()
            )
                .also { mResourceConverter = it }
    }

    /**
     * @return [Converter.Factory] set up for TokenD JSON API
     *
     * Notice that the first call may cause a delay
     *
     * @see getResourceConverter
     */
    @JvmStatic
    fun getJsonApiConverterFactory(): JSONAPIConverterFactory = synchronized(this) {
        return mJsonApiConverterFactory

            ?: JSONAPIConverterFactory(getResourceConverter())
                .also { mJsonApiConverterFactory = it }
    }

    @JvmStatic
    fun getJacksonConverterFactory(): JacksonConverterFactory {
        return JacksonConverterFactory.create(objectMapper)
    }

    @JvmStatic
    val objectMapper: ObjectMapper
        get() = synchronized(this) {
            mObjectMapper
                ?: createBaseObjectMapper()
                    .also { mObjectMapper = it }
        }


    private fun createBaseObjectMapper(): ObjectMapper {
        val mapper = ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)

        val tokenDSerializationModule = SimpleModule().apply {
            addSerializer(Date::class.java, getDateSerializer())
            addDeserializer(Date::class.java, getDateDeserializer())

            addSerializer(BigDecimal::class.java, getBigDecimalSerializer())
            addDeserializer(BigDecimal::class.java, getBigDecimalDeserializer())
        }

        val kotlinModule = KotlinModule.Builder()
            .build()

        mapper.registerModules(tokenDSerializationModule, kotlinModule)

        return mapper
    }

    private fun getDateSerializer(): JsonSerializer<Date> {
        return object : JsonSerializer<Date>() {
            override fun serialize(
                value: Date?,
                gen: JsonGenerator?,
                serializers: SerializerProvider?
            ) {
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
            override fun deserialize(
                p: JsonParser?,
                ctxt: DeserializationContext?
            ): Date? {
                val value = p?.valueAsString
                    ?: return null

                return ApiDateUtil.tryParseDate(value)
            }
        }
    }

    private fun getBigDecimalSerializer(): JsonSerializer<BigDecimal> {
        return object : JsonSerializer<BigDecimal>() {
            override fun serialize(
                value: BigDecimal?,
                gen: JsonGenerator?,
                serializers: SerializerProvider?
            ) {
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
            override fun deserialize(
                p: JsonParser?,
                ctxt: DeserializationContext?
            ): BigDecimal? {
                val value = p?.valueAsString
                    ?: return null

                return BigDecimalUtil.valueOf(value)
            }
        }
    }

    @TestOnly
    @JvmStatic
    fun reset() {
        mResourceConverter = null
        mObjectMapper = null
        mJsonApiConverterFactory = null
    }

    private var mResourceConverter: ResourceConverter? = null
    private var mObjectMapper: ObjectMapper? = null
    private var mJsonApiConverterFactory: JSONAPIConverterFactory? = null
}