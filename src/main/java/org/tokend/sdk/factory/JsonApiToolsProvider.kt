package org.tokend.sdk.factory

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.module.SimpleModule
import com.github.jasminb.jsonapi.ResourceConverter
import com.github.jasminb.jsonapi.retrofit.JSONAPIConverterFactory
import org.tokend.sdk.api.generated.resources.AllResources
import org.tokend.sdk.api.identity.model.IdentityResource
import org.tokend.sdk.utils.ApiDateUtil
import org.tokend.sdk.utils.BigDecimalUtil
import retrofit2.Converter
import java.math.BigDecimal
import java.util.*

/**
 * Constructs and provides base [ObjectMapper] and base [JSONAPIConverterFactory]
 * with type adapters for SDK models.
 */
object JsonApiToolsProvider {
    /**
     * @return [ResourceConverter] set up for TokenD JSON API.
     *
     * Notice that the first call may cause a delay
     */
    @JvmStatic
    fun getResourceConverter(): ResourceConverter = synchronized(this) {
        return resourceConverter
                ?: ResourceConverter(getObjectMapper(),
                        *AllResources.ARRAY,
                        IdentityResource::class.java
                )
                        .also { resourceConverter = it }
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
        return jsonApiConverterFactory

                ?: JSONAPIConverterFactory(getResourceConverter())
                        .also { jsonApiConverterFactory = it }
    }

    @JvmStatic
    fun getObjectMapper(): ObjectMapper = synchronized(this) {
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

    private var resourceConverter: ResourceConverter? = null
    private var objectMapper: ObjectMapper? = null
    private var jsonApiConverterFactory: JSONAPIConverterFactory? = null
}