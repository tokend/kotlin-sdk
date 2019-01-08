package org.tokend.sdk.api.v2

import org.tokend.sdk.api.base.BaseApi
import org.tokend.sdk.api.v2.accounts.AccountsApiV2
import org.tokend.sdk.api.v2.accounts.AccountsServiceV2
import org.tokend.sdk.api.v2.assetpairs.AssetPairsApi
import org.tokend.sdk.api.v2.assetpairs.AssetPairsService
import org.tokend.sdk.api.v2.assets.AssetsApiV2
import org.tokend.sdk.api.v2.assets.AssetsServiceV2
import org.tokend.sdk.api.v2.balances.BalancesApi
import org.tokend.sdk.api.v2.balances.BalancesService
import org.tokend.sdk.api.v2.fees.FeesApiV2
import org.tokend.sdk.api.v2.fees.FeesServiceV2
import org.tokend.sdk.api.v2.keyvalue.KeyValueStorageApiV2
import org.tokend.sdk.api.v2.keyvalue.KeyValueStorageServiceV2
import org.tokend.sdk.api.v2.offers.OffersApiV2
import org.tokend.sdk.api.v2.offers.OffersServiceV2
import org.tokend.sdk.api.v2.requests.RequestsApiV2
import org.tokend.sdk.api.v2.requests.RequestsServiceV2
import org.tokend.sdk.api.v2.sales.SalesApiV2
import org.tokend.sdk.api.v2.sales.SalesServiceV2
import org.tokend.sdk.api.v2.transactions.TransactionsApiV2
import org.tokend.sdk.api.v2.transactions.TransactionsServiceV2
import org.tokend.sdk.signing.RequestSigner
import org.tokend.sdk.tfa.TfaCallback
import org.tokend.sdk.utils.CookieJarProvider

open class TokenDApiV2(rootUrl: String,
                       requestSigner: RequestSigner?,
                       tfaCallback: TfaCallback?,
                       cookieJarProvider: CookieJarProvider?,
                       userAgent: String?,
                       withLogs: Boolean
) : BaseApi(
        rootUrl, requestSigner, tfaCallback, cookieJarProvider,
        userAgent, true, withLogs
) {
    open val accounts: AccountsApiV2 by lazy {
        AccountsApiV2(getService(AccountsServiceV2::class.java))
    }

    open val assetPairs: AssetPairsApi by lazy {
        AssetPairsApi(getService(AssetPairsService::class.java))
    }

    open val assets: AssetsApiV2 by lazy {
        AssetsApiV2(getService(AssetsServiceV2::class.java))
    }

    open val balances: BalancesApi by lazy {
        BalancesApi(getService(BalancesService::class.java))
    }

    open val fees: FeesApiV2 by lazy {
        FeesApiV2(getService(FeesServiceV2::class.java))
    }

    open val keyValue: KeyValueStorageApiV2 by lazy {
        KeyValueStorageApiV2(getService(KeyValueStorageServiceV2::class.java))
    }

    open val offers: OffersApiV2 by lazy {
        OffersApiV2(getService(OffersServiceV2::class.java))
    }

    open val transactions: TransactionsApiV2 by lazy {
        TransactionsApiV2(getService(TransactionsServiceV2::class.java))
    }

    open val requests: RequestsApiV2 by lazy {
        RequestsApiV2(getService(RequestsServiceV2::class.java))
    }

    open val sales: SalesApiV2 by lazy {
        SalesApiV2(getService(SalesServiceV2::class.java))
    }
}