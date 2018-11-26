package org.tokend.sdk.api.authenticator.model

import org.tokend.wallet.xdr.SignerType
import java.net.URI
import java.net.URLDecoder
import java.net.URLEncoder
import java.util.*

/**
 * Represents public key authorization request.
 */
class AuthRequest(
        val networkUrl: String,
        val appName: String,
        val scope: Int,
        val publicKey: String,
        val expirationDate: Date?
) {
    val accessTypes: List<SignerType> =
            SignerType.values().fold(mutableListOf()) { result, type ->
                if (type.value and scope == type.value) {
                    result.add(type)
                }
                result
            }

    val uriString =
            "$URI_SCHEME://$URI_HOST" +
                    "?$URI_NETWORK_URL_KEY=${URLEncoder.encode(networkUrl, URLENCODE_CHARSET)}" +
                    "&$URI_APP_NAME_KEY=${URLEncoder.encode(appName, URLENCODE_CHARSET)}" +
                    "&$URI_PUBKEY_KEY=${URLEncoder.encode(publicKey, URLENCODE_CHARSET)}" +
                    "&$URI_SCOPE_KEY=$scope" +
                    "&$URI_EXPIRATION_TIMESTAMP_KEY=${
                    if (expirationDate != null)
                        expirationDate.time / 1000
                    else
                        0
                    }"

    companion object {
        private const val URI_SCHEME = "tokend"
        private const val URI_HOST = "auth"
        private const val URI_NETWORK_URL_KEY = "api"
        private const val URI_APP_NAME_KEY = "app"
        private const val URI_PUBKEY_KEY = "pubkey"
        private const val URI_SCOPE_KEY = "scope"
        private const val URI_EXPIRATION_TIMESTAMP_KEY = "expires_at"
        private const val URLENCODE_CHARSET = "UTF-8"

        /**
         * @return parsed [AuthRequest] from URI string.
         * @throws IllegalArgumentException if URI string is invalid in some way
         */
        @JvmStatic
        fun parse(uriString: String): AuthRequest {
            val uri = URI.create(uriString)

            val scheme = uri.scheme
            if (scheme != URI_SCHEME) {
                throw IllegalArgumentException("Auth URI must have '$URI_SCHEME' scheme")

            }

            val host = uri.host
            if (host != URI_HOST) {
                throw IllegalArgumentException("Auth URI must have '$URI_HOST' host")
            }

            val queryMap = uri.query
                    .splitToSequence("&")
                    .associate { queryPair ->
                        val splitted = queryPair.split("=")
                        splitted[0] to URLDecoder.decode(splitted[1], URLENCODE_CHARSET)
                    }

            return AuthRequest(
                    networkUrl =
                    queryMap[URI_NETWORK_URL_KEY]
                            ?: throw IllegalArgumentException("No API URL specified"),
                    appName = queryMap[URI_APP_NAME_KEY]
                            ?: throw IllegalArgumentException("No app name specified"),
                    scope =
                    queryMap[URI_SCOPE_KEY]?.toInt()
                            ?: throw IllegalArgumentException("Invalid or missing scope"),
                    publicKey =
                    queryMap[URI_PUBKEY_KEY]
                            ?: throw IllegalArgumentException("Invalid or missing public key"),
                    expirationDate = queryMap[URI_EXPIRATION_TIMESTAMP_KEY]
                            ?.toLongOrNull()
                            ?.takeIf { it > 0 }
                            ?.let { Date(it * 1000) }
            )
        }
    }
}