package org.tokend.sdk.uri

import java.net.URI
import java.net.URLDecoder
import java.net.URLEncoder

open class TokenDUri
private constructor(
        val host: String,
        private val queryMapEncoded: Map<String, String>
) {
    open class Builder(
            private var host: String
    ) {
        private val queryMap = mutableMapOf<String, String>()

        fun setHost(host: String): Builder {
            this.host = host
            return this
        }

        fun addQueryParam(key: String, value: String): Builder {
            queryMap[key] = URLEncoder.encode(value, URLENCODE_CHARSET)
            return this
        }

        fun build(): TokenDUri {
            return TokenDUri(host, queryMap)
        }
    }

    val query: String
        get() = if (queryMapEncoded.isEmpty())
            ""
        else
            "?" + queryMapEncoded
                    .map { (key, value) -> "$key=$value" }
                    .joinToString("&")

    fun getQueryParam(key: String): String? {
        val encoded = queryMapEncoded[key]
        return URLDecoder.decode(encoded, URLENCODE_CHARSET)
    }

    override fun toString(): String {
        return "$URI_SCHEME://$host$query"
    }

    companion object {
        private const val URLENCODE_CHARSET = "UTF-8"
        private const val URI_SCHEME = "tokend"

        /**
         * @return parsed [TokenDUri] from URI string.
         * @throws IllegalArgumentException if URI string is invalid in some way
         */
        @JvmStatic
        fun parse(uriString: String): TokenDUri {
            val uri = URI.create(uriString)

            val scheme = uri.scheme
            if (scheme != URI_SCHEME) {
                throw IllegalArgumentException("TokenD URI must have '$URI_SCHEME' scheme, " +
                        "got '$scheme' instead")
            }

            val host = uri.host

            val queryMap = uri.rawQuery
                    .splitToSequence("&")
                    .associate { queryPair ->
                        queryPair.split("=").let {
                            it[0] to it[1]
                        }
                    }

            return TokenDUri(host, queryMap)
        }
    }
}