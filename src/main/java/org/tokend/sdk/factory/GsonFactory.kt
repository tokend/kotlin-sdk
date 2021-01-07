package org.tokend.sdk.factory

import com.google.gson.*
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
    fun getNewBaseGsonBuilder(): GsonBuilder {
        return GsonBuilder()
                .serializeNulls()
                .registerTypeAdapter(Date::class.java, getGsonDateSerializer())
                .registerTypeAdapter(Date::class.java, getGsonDateDeserializer())
                .registerTypeAdapter(BigDecimal::class.java, getGsonBigDecimalSerializer())
                .registerTypeAdapter(BigDecimal::class.java, getGsonBigDecimalDeserializer())
    }

    fun getBaseGson(): Gson {
        return baseGson ?: getNewBaseGsonBuilder()
                .create()
                .also { baseGson = it }
    }

    fun getBaseGsonConverterFactory(): GsonConverterFactory {
        return baseConverterFactory ?: GsonConverterFactory.create(getBaseGson())
                .also { baseConverterFactory = it }
    }

    private fun getGsonDateDeserializer(): JsonDeserializer<Date?> {
        return JsonDeserializer { json, _, _ ->
            if (json.isJsonNull)
                null
            else
                ApiDateUtil.tryParseDate(json.asString)
        }
    }

    private fun getGsonDateSerializer(): JsonSerializer<Date?> {
        return JsonSerializer { source, _, _ ->
            if (source == null)
                JsonNull.INSTANCE
            else
                JsonPrimitive(ApiDateUtil.formatDateForRequest(source))
        }
    }

    private fun getGsonBigDecimalDeserializer(): JsonDeserializer<BigDecimal?> {
        return JsonDeserializer { source, _, _ ->
            if (source.isJsonNull)
                null
            else
                BigDecimalUtil.valueOf(source.asString)
        }
    }

    private fun getGsonBigDecimalSerializer(): JsonSerializer<BigDecimal?> {
        return JsonSerializer { source, _, _ ->
            if (source == null)
                JsonNull.INSTANCE
            else
                JsonPrimitive(BigDecimalUtil.toPlainString(source))
        }
    }

    companion object {
        private var baseGson: Gson? = null
        private var baseConverterFactory: GsonConverterFactory? = null
    }
}