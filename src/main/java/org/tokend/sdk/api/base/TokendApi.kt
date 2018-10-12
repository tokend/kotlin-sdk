package org.tokend.sdk.api.base

import org.tokend.sdk.api.accounts.AccountsApi
import org.tokend.sdk.api.accounts.AccountsService
import org.tokend.sdk.api.blobs.BlobsApi
import org.tokend.sdk.api.blobs.BlobsService
import org.tokend.sdk.api.favorites.FavoritesApi
import org.tokend.sdk.api.favorites.FavoritesService
import org.tokend.sdk.api.fees.FeesApi
import org.tokend.sdk.api.fees.FeesService
import org.tokend.sdk.api.general.GeneralApi
import org.tokend.sdk.api.general.GeneralService
import org.tokend.sdk.api.requests.CookieJarProvider
import org.tokend.sdk.api.requests.RequestSigner
import org.tokend.sdk.api.sales.SalesApi
import org.tokend.sdk.api.sales.SalesService
import org.tokend.sdk.api.tfa.TfaApi
import org.tokend.sdk.api.tfa.TfaService
import org.tokend.sdk.api.tfa.model.TfaCallback
import org.tokend.sdk.api.transactions.TransactionsApi
import org.tokend.sdk.api.transactions.TransactionsService
import org.tokend.sdk.api.users.UsersApi
import org.tokend.sdk.api.users.UsersService

open class TokendApi
@JvmOverloads constructor(
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
}