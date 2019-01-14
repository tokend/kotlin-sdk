package org.tokend.sdk.api.authenticator.model

import org.tokend.sdk.uri.TokenDUri
import org.tokend.wallet.xdr.SignerType
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

    val uri = TokenDUri.Builder(URI_HOST)
            .addQueryParam(URI_NETWORK_URL_KEY, networkUrl)
            .addQueryParam(URI_APP_NAME_KEY, appName)
            .addQueryParam(URI_PUBKEY_KEY, publicKey)
            .addQueryParam(URI_SCOPE_KEY, scope.toString())
            .addQueryParam(URI_EXPIRATION_TIMESTAMP_KEY,
                    "${
                    if (expirationDate != null)
                        expirationDate.time / 1000
                    else
                        0
                    }"
            )
            .build()

    val uriString = uri.toString()

    companion object {
        private const val URI_HOST = "auth"
        private const val URI_NETWORK_URL_KEY = "api"
        private const val URI_APP_NAME_KEY = "app"
        private const val URI_PUBKEY_KEY = "pubkey"
        private const val URI_SCOPE_KEY = "scope"
        private const val URI_EXPIRATION_TIMESTAMP_KEY = "expires_at"

        /**
         * @return parsed [AuthRequest] from URI string.
         * @throws IllegalArgumentException if URI string is invalid in some way
         */
        @JvmStatic
        fun parse(uriString: String): AuthRequest {
            val uri = TokenDUri.parse(uriString)

            val host = uri.host
            if (host != URI_HOST) {
                throw IllegalArgumentException("Auth URI must have '$URI_HOST' host")
            }

            return AuthRequest(
                    networkUrl =
                    uri.getQueryParam(URI_NETWORK_URL_KEY)
                            ?: throw IllegalArgumentException("No API URL specified"),
                    appName = uri.getQueryParam(URI_APP_NAME_KEY)
                            ?: throw IllegalArgumentException("No app name specified"),
                    scope =
                    uri.getQueryParam(URI_SCOPE_KEY)?.toInt()
                            ?: throw IllegalArgumentException("Invalid or missing scope"),
                    publicKey =
                    uri.getQueryParam(URI_PUBKEY_KEY)
                            ?: throw IllegalArgumentException("Invalid or missing public key"),
                    expirationDate = uri.getQueryParam(URI_EXPIRATION_TIMESTAMP_KEY)
                            ?.toLongOrNull()
                            ?.takeIf { it > 0 }
                            ?.let { Date(it * 1000) }
            )
        }
    }
}