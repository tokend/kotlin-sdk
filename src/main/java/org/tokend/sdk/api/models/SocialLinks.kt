package org.tokend.sdk.api.models

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.tokend.sdk.api.base.factory.GsonFactory
import java.lang.reflect.Type

open class SocialLinks(val items: List<String>) {
    companion object {
        private const val ARRAY_KEY = "parsedArray"
    }

    class SocialLinksDeserializer : JsonDeserializer<SocialLinks> {
        override fun deserialize(json: JsonElement?,
                                 typeOfT: Type?,
                                 context: JsonDeserializationContext?): SocialLinks {
            val string = json?.asString
            val obj = GsonFactory().getBaseGson().fromJson(string, JsonObject::class.java)
            val linksArray =
                    if (obj.has(ARRAY_KEY))
                        obj?.getAsJsonArray(ARRAY_KEY)
                    else
                        null

            val resultArray = mutableListOf<String>()
            linksArray?.forEach {
                val linkString = it?.asString
                linkString?.let {
                    resultArray.add(it)
                }
            }

            return SocialLinks(resultArray)
        }
    }

    val twitter: String?
        get() = items.firstOrNull { it.contains("twitter.com/") }

    val facebook: String?
        get() = items.firstOrNull { it.contains("facebook.com/") }

    val linkedIn: String?
        get() = items.firstOrNull { it.contains("linkedin.com/") }
}