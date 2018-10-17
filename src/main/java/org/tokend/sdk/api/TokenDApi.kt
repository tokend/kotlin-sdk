package org.tokend.sdk.api

import org.tokend.sdk.api.accounts.AccountsApi
import org.tokend.sdk.api.accounts.AccountsService
import org.tokend.sdk.api.assets.AssetsApi
import org.tokend.sdk.api.assets.AssetsService
import org.tokend.sdk.api.base.BaseApi
import org.tokend.sdk.api.blobs.BlobsApi
import org.tokend.sdk.api.blobs.BlobsService
import org.tokend.sdk.api.favorites.FavoritesApi
import org.tokend.sdk.api.favorites.FavoritesService
import org.tokend.sdk.api.fees.FeesApi
import org.tokend.sdk.api.fees.FeesService
import org.tokend.sdk.api.general.GeneralApi
import org.tokend.sdk.api.general.GeneralService
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
 *
 * @see [AccountRequestSigner]
 */
@JvmOverloads
constructor(
        rootUrl: String,
        requestSigner: RequestSigner? = null,
        tfaCallback: TfaCallback? = null,
        cookieJarProvider: CookieJarProvider? = null,
        userAgent: String? = null
) : BaseApi(rootUrl, requestSigner, tfaCallback, cookieJarProvider, userAgent) {
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
}