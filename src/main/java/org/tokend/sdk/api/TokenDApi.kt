package org.tokend.sdk.api

import org.tokend.sdk.api.accounts.AccountsApi
import org.tokend.sdk.api.accounts.AccountsService
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.BaseApi
import org.tokend.sdk.api.blobs.BlobsApi
import org.tokend.sdk.api.charts.ChartsApi
import org.tokend.sdk.api.charts.ChartsService
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.custom.CustomRequestsService
import org.tokend.sdk.api.documents.DocumentsApi
import org.tokend.sdk.api.documents.DocumentsService
import org.tokend.sdk.api.fees.FeesApi
import org.tokend.sdk.api.fees.FeesService
import org.tokend.sdk.api.identity.IdentitiesApi
import org.tokend.sdk.api.identity.IdentitiesService
import org.tokend.sdk.api.integrations.IntegrationsApi
import org.tokend.sdk.api.tfa.TfaApi
import org.tokend.sdk.api.tfa.TfaService
import org.tokend.sdk.api.v3.TokenDApiV3
import org.tokend.sdk.api.wallets.WalletsApi
import org.tokend.sdk.api.wallets.WalletsService
import org.tokend.sdk.factory.JsonApiTools
import org.tokend.sdk.keyserver.KeyServer
import org.tokend.sdk.signing.AccountRequestSigner
import org.tokend.sdk.signing.RequestSigner
import org.tokend.sdk.tfa.TfaCallback
import org.tokend.sdk.utils.CookieJarProvider
import java.util.concurrent.Executor

open class TokenDApi
/**
 * Creates TokenD API instance.
 * @param rootUrl root URL of TokenD instance
 * @param requestSigner required to perform requests
 * with signature check. If not set such requests will be uncompletable
 * @param tfaCallback required to handle 2FA (2 factor auth) requests.
 * If not set requests protected by 2FA will be uncompletable
 * @param cookieJarProvider if set will be used to store cookies
 * @param extraHeaders  add headers to all requests
 * @param withLogs enable/disable HTTP Logs. True by default.
 * @param asyncCallbackExecutor [Executor] that performs [ApiRequest.executeAsync] callback.
 * By default, it is [BaseApi.DEFAULT_ASYNC_CALLBACK_EXECUTOR], you may
 * want to use Android main thread executor for this.
 *
 * @see [AccountRequestSigner]
 */
@JvmOverloads
constructor(
    rootUrl: String,
    requestSigner: RequestSigner? = null,
    tfaCallback: TfaCallback? = null,
    cookieJarProvider: CookieJarProvider? = null,
    extraHeaders: Map<String, String?>? = null,
    withLogs: Boolean = true,
    asyncCallbackExecutor: Executor = DEFAULT_ASYNC_CALLBACK_EXECUTOR
) : BaseApi(
    rootUrl, requestSigner, tfaCallback, cookieJarProvider, extraHeaders,
    asyncCallbackExecutor, withLogs
) {

    open val v3: TokenDApiV3 by lazy {
        TokenDApiV3(
            rootUrl, requestSigner, tfaCallback, cookieJarProvider, extraHeaders, withLogs,
            asyncCallbackExecutor
        )
    }

    /**
     * 🚨 Integration modules APIs. Backward compatibility is not guaranteed.
     */
    open val integrations: IntegrationsApi by lazy {
        IntegrationsApi(
            rootUrl, requestSigner, tfaCallback, cookieJarProvider, extraHeaders, withLogs,
            asyncCallbackExecutor
        )
    }

    open val accounts: AccountsApi by lazy {
        AccountsApi(getService(AccountsService::class.java))
    }

    open val blobs: BlobsApi by lazy {
        BlobsApi(customRequests)
    }

    open val fees: FeesApi by lazy {
        FeesApi(getService(FeesService::class.java))
    }

    open val tfa: TfaApi by lazy {
        TfaApi(getService(TfaService::class.java))
    }

    open val charts: ChartsApi by lazy {
        ChartsApi(getService(ChartsService::class.java))
    }

    /**
     * @see [KeyServer]
     */
    open val wallets: WalletsApi by lazy {
        WalletsApi(getService(WalletsService::class.java))
    }

    open val documents: DocumentsApi by lazy {
        DocumentsApi(getService(DocumentsService::class.java))
    }

    open val identities: IdentitiesApi by lazy {
        IdentitiesApi(getService(IdentitiesService::class.java))
    }

    /**
     * Allows making custom HTTP requests with response body mapping.
     *
     * [JsonApiTools] is used for mapping (both JSONAPI resources and regular models).
     * If the response class is [String] or [ByteArray] or primitive Java byte array,
     * then no mapping will be performed.
     */
    open val customRequests: CustomRequestsApi by lazy {
        CustomRequestsApi(getService(CustomRequestsService::class.java))
    }

    /**
     * Builds TokenDApi instance.
     * @param rootUrl root URL of TokenD instance.
     */
    class Builder(private val rootUrl: String) {
        private var requestSigner: RequestSigner? = null
        private var tfaCallback: TfaCallback? = null
        private var cookieJarProvider: CookieJarProvider? = null
        private var extraHeaders: Map<String, String?>? = null
        private var withLogs: Boolean = true
        private var asyncCallbackExecutor: Executor = DEFAULT_ASYNC_CALLBACK_EXECUTOR

        /**
         * Required to perform requests with signature check.
         * If not set such requests will be uncompletable.
         */
        fun setRequestSigner(requestSigner: RequestSigner): Builder {
            this.requestSigner = requestSigner
            return this
        }

        /**
         * Required to handle 2FA (2 factor auth) requests.
         * If not set requests protected by 2FA will be uncompletable.
         */
        fun setTfaCallback(tfaCallback: TfaCallback): Builder {
            this.tfaCallback = tfaCallback
            return this
        }

        /**
         * If set will be used to store cookies.
         */
        fun setCookieJarProvider(cookieJarProvider: CookieJarProvider): Builder {
            this.cookieJarProvider = cookieJarProvider
            return this
        }

        /**
         * Set extra headers.
         */
        fun setExtraHeaders(extraHeaders: Map<String, String?>?): Builder {
            this.extraHeaders = extraHeaders
            return this
        }

        /**
         * Overrides default user agent.
         */
        fun setUserAgent(userAgent: String): Builder {
            setExtraHeaders(mapOf(HEADER_USER_AGENT_NAME to userAgent))
            return this
        }

        /**
         * Enable/disable HTTP Logs. True by default.
         */
        fun withLogs(withLogs: Boolean): Builder {
            this.withLogs = withLogs
            return this
        }

        /**
         * [Executor] that performs [ApiRequest.executeAsync] callback.
         * By default it is [BaseApi.DEFAULT_ASYNC_CALLBACK_EXECUTOR], you may
         * want to use Android main thread executor for this.
         */
        fun setAsyncCallbackExecutor(asyncCallbackExecutor: Executor): Builder {
            this.asyncCallbackExecutor = asyncCallbackExecutor
            return this
        }

        fun build() = TokenDApi(
            rootUrl,
            requestSigner,
            tfaCallback,
            cookieJarProvider,
            extraHeaders,
            withLogs,
            asyncCallbackExecutor
        )
    }

    companion object {
        private const val HEADER_USER_AGENT_NAME = "User-Agent"
    }
}