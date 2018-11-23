package org.tokend.sdk.factory

import com.google.gson.*
import org.tokend.sdk.api.sales.model.SocialLinks
import org.tokend.sdk.utils.ApiDateUtil
import org.tokend.sdk.utils.BigDecimalUtil
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigDecimal
import java.util.*

/**
 * Constructs and provides base Gson and base converter factory
 * with type adapters for SDK models.
 */
class GsonFactory {
    fun getBaseGson(): Gson {
        return baseGson ?: GsonBuilder()
                .serializeNulls()
                .registerTypeAdapter(SocialLinks::class.java, SocialLinks.SocialLinksDeserializer())
                .registerTypeAdapter(Date::class.java, getGsonDateSerializer())
                .registerTypeAdapter(Date::class.java, getGsonDateDeserializer())
                .registerTypeAdapter(BigDecimal::class.java, getGsonBigDecimalSerializer())
                .registerTypeAdapter(BigDecimal::class.java, getGsonBigDecimalDeserializer())
                .create()
                .also { baseGson = it }
    }

    fun getBaseGsonConverterFactory(): GsonConverterFactory {
        return baseConverterFactory ?: GsonConverterFactory.create(getBaseGson())
                .also { baseConverterFactory = it }
    }

    private fun getGsonDateDeserializer(): JsonDeserializer<Date?> {
        return JsonDeserializer { json, _, _ ->
            ApiDateUtil.tryParseDate(json?.asString)
        }
    }

    private fun getGsonDateSerializer(): JsonSerializer<Date?> {
        return JsonSerializer { source, _, _ ->
            JsonPrimitive(ApiDateUtil.formatDateForRequest(source))
        }
    }

    private fun getGsonBigDecimalDeserializer(): JsonDeserializer<BigDecimal?> {
        return JsonDeserializer { source, _, _ ->
            BigDecimalUtil.valueOf(source?.asString)
        }
    }

    private fun getGsonBigDecimalSerializer(): JsonSerializer<BigDecimal?> {
        return JsonSerializer { source, _, _ ->
            JsonPrimitive(BigDecimalUtil.toPlainString(source))
        }
    }

    companion object {
        private var baseGson: Gson? = null
        private var baseConverterFactory: GsonConverterFactory? = null
    }
}