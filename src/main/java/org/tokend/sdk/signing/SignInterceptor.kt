package org.tokend.sdk.signing

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.internal.http.HttpDate
import org.tokend.wallet.utils.Hashing
import java.util.*

internal open class SignInterceptor(
        private val baseUrl: String,
        private val requestSigner: RequestSigner
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = buildSignedRequest(chain)
        return chain.proceed(newRequest)
    }

    protected open fun buildSignedRequest(chain: Interceptor.Chain): Request {
        val method = chain.request().method()

        val url = chain.request().url().url()
        var urlPartToSign = url.toString().substringAfter(baseUrl,
                "${url.path}?${url.query}")
        if (!urlPartToSign.startsWith("/")) {
            urlPartToSign = "/$urlPartToSign"
        }

        val requestTarget = "${method.toLowerCase()} $urlPartToSign"

        val dateHeaderContent = HttpDate.format(Date())

        val authHeaderContent = buildHttpAuthHeader(
                requestSigner,
                DATE_HEADER to dateHeaderContent,
                REQUEST_TARGET_HEADER to requestTarget
        )

        return chain.request().newBuilder()
                .addHeader(ACCOUNT_HEADER, requestSigner.accountId)
                .addHeader(AUTH_HEADER, authHeaderContent)
                .addHeader(DATE_HEADER, dateHeaderContent)
                .build()
    }

    /**
     * @return auth header content according to the signing requirements
     * @see <a href="https://tokend.gitbook.io/knowledge-base/technical-details/security#rest-api">Requests signing on Knowledge base</a>
     */
    private fun buildHttpAuthHeader(requestSigner: RequestSigner,
                                    vararg headerContents: Pair<String, String?>): String {
        val contentToSign = buildHttpSignatureContent(headerContents)
        val hashToSign = Hashing.sha256(contentToSign)

        val signatureBase64 = requestSigner.signToBase64(hashToSign)
        val keyId = requestSigner.keyId
        val headersString = headerContents.joinToString(" ") { it.first.toLowerCase() }

        return "keyId=\"$keyId\",algorithm=\"$AUTH_ALGORITHM\"" +
                ",signature=\"$signatureBase64\",headers=\"$headersString\""
    }

    /**
     * @return string where each row contains header name in lower case and it's content
     */
    private fun buildHttpSignatureContent(headerContents: Array<out Pair<String, String?>>)
            : ByteArray {
        val string = buildString {
            headerContents.forEachIndexed { i, headerPair ->
                append("${headerPair.first.toLowerCase()}: ${headerPair.second}")
                if (i != headerContents.size - 1) {
                    append('\n')
                }
            }
        }

        return string.toByteArray(SIGNATURE_STRING_CHARSET)
    }

    companion object {
        const val REQUEST_TARGET_HEADER = "(request-target)"
        const val DATE_HEADER = "Date"
        const val AUTH_HEADER = "Authorization"
        const val ACCOUNT_HEADER = "Account"
        const val AUTH_ALGORITHM = "ed25519-sha256"
        @JvmStatic
        val SIGNATURE_STRING_CHARSET = Charsets.UTF_8
    }
}