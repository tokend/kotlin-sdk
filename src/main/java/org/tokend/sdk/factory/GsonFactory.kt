package org.tokend.sdk.factory

import com.google.gson.*
import org.tokend.sdk.api.models.SocialLinks
import org.tokend.sdk.utils.ApiDateUtil
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class GsonFactory {
    fun getBaseGson(): Gson {
        return baseGson ?: GsonBuilder()
                .serializeNulls()
                .registerTypeAdapter(SocialLinks::class.java, SocialLinks.SocialLinksDeserializer())
                .registerTypeAdapter(Date::class.java, getGsonDateSerializer())
                .registerTypeAdapter(Date::class.java, getGsonDateDeserializer())
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

    companion object {
        private var baseGson: Gson? = null
        private var baseConverterFactory: GsonConverterFactory? = null
    }
}