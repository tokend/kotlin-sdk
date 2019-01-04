package org.tokend.sdk.api

import org.tokend.sdk.api.accounts.AccountsApi
import org.tokend.sdk.api.accounts.AccountsService
import org.tokend.sdk.api.assets.AssetsApi
import org.tokend.sdk.api.assets.AssetsService
import org.tokend.sdk.api.authenticator.AuthResultsApi
import org.tokend.sdk.api.authenticator.AuthResultsService
import org.tokend.sdk.api.base.BaseApi
import org.tokend.sdk.api.blobs.BlobsApi
import org.tokend.sdk.api.blobs.BlobsService
import org.tokend.sdk.api.documents.DocumentsApi
import org.tokend.sdk.api.documents.DocumentsService
import org.tokend.sdk.api.favorites.FavoritesApi
import org.tokend.sdk.api.favorites.FavoritesService
import org.tokend.sdk.api.fees.FeesApi
import org.tokend.sdk.api.fees.FeesService
import org.tokend.sdk.api.general.GeneralApi
import org.tokend.sdk.api.general.GeneralService
import org.tokend.sdk.api.keyvaluestorage.KeyValueStorageApi
import org.tokend.sdk.api.keyvaluestorage.KeyValueStorageService
import org.tokend.sdk.api.requests.RequestsApi
import org.tokend.sdk.api.requests.RequestsService
import org.tokend.sdk.api.sales.SalesApi
import org.tokend.sdk.api.sales.SalesService
import org.tokend.sdk.api.tfa.TfaApi
import org.tokend.sdk.api.tfa.TfaService
import org.tokend.sdk.api.trades.TradesApi
import org.tokend.sdk.api.trades.TradesService
import org.tokend.sdk.api.transactions.TransactionsApi
import org.tokend.sdk.api.transactions.TransactionsService
import org.tokend.sdk.api.users.UsersApi
import org.tokend.sdk.api.users.UsersService
import org.tokend.sdk.api.v2.TokenDApiV2
import org.tokend.sdk.api.wallets.WalletsApi
import org.tokend.sdk.api.wallets.WalletsService
import org.tokend.sdk.signing.AccountRequestSigner
import org.tokend.sdk.signing.RequestSigner
import org.tokend.sdk.tfa.TfaCallback
import org.tokend.sdk.utils.CookieJarProvider

open class TokenDApi
/**
 * Creates TokenD API instance.
 * @param rootUrl root URL of TokenD instance
 * @param requestSigner required to perform requests
 * with signature check. If not set such requests will be uncompletable
 * @param tfaCallback required to handle 2FA (2 factor auth) requests.
 * If not set requests protected by 2FA will be uncompletable
 * @param cookieJarProvider if set will be used to store cookies
 * @param userAgent overrides default user agent
 * @param forceContentType send 'Accept' header with specific content type, affects errors format
 * @param withLogs enable/disable HTTP Logs. True by default.
 *
 * @see [AccountRequestSigner]
 */
@JvmOverloads
constructor(
        rootUrl: String,
        requestSigner: RequestSigner? = null,
        tfaCallback: TfaCallback? = null,
        cookieJarProvider: CookieJarProvider? = null,
        userAgent: String? = null,
        forceContentType: Boolean = false,
        withLogs: Boolean = true
) : BaseApi(rootUrl, requestSigner, tfaCallback, cookieJarProvider, userAgent, forceContentType, withLogs) {
    open val v2: TokenDApiV2 by lazy {
        TokenDApiV2(rootUrl, requestSigner, tfaCallback, cookieJarProvider, userAgent, withLogs)
    }

    open val accounts: AccountsApi by lazy {
        AccountsApi(getService(AccountsService::class.java))
    }

    open val transactions: TransactionsApi by lazy {
        TransactionsApi(getService(TransactionsService::class.java))
    }

    open val users: UsersApi by lazy {
        UsersApi(getService(UsersService::class.java))
    }

    open val sales: SalesApi by lazy {
        SalesApi(getService(SalesService::class.java))
    }

    open val blobs: BlobsApi by lazy {
        BlobsApi(getService(BlobsService::class.java))
    }

    open val fees: FeesApi by lazy {
        FeesApi(getService(FeesService::class.java))
    }

    open val tfa: TfaApi by lazy {
        TfaApi(getService(TfaService::class.java))
    }

    open val favorites: FavoritesApi by lazy {
        FavoritesApi(getService(FavoritesService::class.java))
    }

    open val general: GeneralApi by lazy {
        GeneralApi(getService(GeneralService::class.java))
    }

    open val trades: TradesApi by lazy {
        TradesApi(getService(TradesService::class.java))
    }

    open val assets: AssetsApi by lazy {
        AssetsApi(getService(AssetsService::class.java))
    }

    open val wallets: WalletsApi by lazy {
        WalletsApi(getService(WalletsService::class.java))
    }

    open val keyValueEntries: KeyValueStorageApi by lazy {
        KeyValueStorageApi(getService(KeyValueStorageService::class.java))
    }

    open val documents: DocumentsApi by lazy {
        DocumentsApi(getService(DocumentsService::class.java))
    }

    open val requests: RequestsApi by lazy {
        RequestsApi(getService(RequestsService::class.java))
    }

    open val authResults: AuthResultsApi by lazy {
        AuthResultsApi(getService(AuthResultsService::class.java))
    }

    /**
     * Builds TokenDApi instance.
     * @param rootUrl root URL of TokenD instance.
     */
    class Builder(private val rootUrl: String) {
        private var requestSigner: RequestSigner? = null
        private var tfaCallback: TfaCallback? = null
        private var cookieJarProvider: CookieJarProvider? = null
        private var userAgent: String? = null
        private var forceContentType: Boolean = false
        private var withLogs: Boolean = true

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
         * Overrides default user agent.
         */
        fun setUserAgent(userAgent: String): Builder {
            this.userAgent = userAgent
            return this
        }

        /**
         * Send 'Accept' header with specific content type, affects errors format.
         */
        fun forceContentType(forceContentType: Boolean): Builder {
            this.forceContentType = forceContentType
            return this
        }

        /**
         * Enable/disable HTTP Logs. True by default.
         */
        fun withLogs(withLogs: Boolean): Builder {
            this.withLogs = withLogs
            return this
        }

        fun build() = TokenDApi(
                rootUrl,
                requestSigner,
                tfaCallback,
                cookieJarProvider,
                userAgent,
                forceContentType,
                withLogs)
    }

}