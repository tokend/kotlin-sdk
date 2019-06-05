package org.tokend.sdk.api.v3

import org.tokend.sdk.api.base.BaseApi
import org.tokend.sdk.api.v3.accounts.AccountsApiV3
import org.tokend.sdk.api.v3.accounts.AccountsServiceV3
import org.tokend.sdk.api.v3.assetpairs.AssetPairsApi
import org.tokend.sdk.api.v3.assetpairs.AssetPairsService
import org.tokend.sdk.api.v3.assets.AssetsApiV3
import org.tokend.sdk.api.v3.assets.AssetsServiceV3
import org.tokend.sdk.api.v3.balances.BalancesApi
import org.tokend.sdk.api.v3.balances.BalancesService
import org.tokend.sdk.api.v3.fees.FeesApiV3
import org.tokend.sdk.api.v3.fees.FeesServiceV3
import org.tokend.sdk.api.v3.history.HistoryApi
import org.tokend.sdk.api.v3.history.HistoryService
import org.tokend.sdk.api.v3.keyvalue.KeyValueStorageApiV3
import org.tokend.sdk.api.v3.keyvalue.KeyValueStorageServiceV3
import org.tokend.sdk.api.v3.offers.OffersApiV3
import org.tokend.sdk.api.v3.offers.OffersServiceV3
import org.tokend.sdk.api.v3.orderbook.OrderBooksApi
import org.tokend.sdk.api.v3.orderbook.OrderBooksService
import org.tokend.sdk.api.v3.polls.PollsApi
import org.tokend.sdk.api.v3.polls.PollsService
import org.tokend.sdk.api.v3.requests.RequestsApiV3
import org.tokend.sdk.api.v3.requests.RequestsServiceV3
import org.tokend.sdk.api.v3.sales.SalesApiV3
import org.tokend.sdk.api.v3.sales.SalesServiceV3
import org.tokend.sdk.api.v3.signers.SignersApiV3
import org.tokend.sdk.api.v3.signers.SignersServiceV3
import org.tokend.sdk.api.v3.transactions.TransactionsApiV3
import org.tokend.sdk.api.v3.transactions.TransactionsServiceV3
import org.tokend.sdk.signing.RequestSigner
import org.tokend.sdk.tfa.TfaCallback
import org.tokend.sdk.utils.CookieJarProvider

open class TokenDApiV3(rootUrl: String,
                       requestSigner: RequestSigner?,
                       tfaCallback: TfaCallback?,
                       cookieJarProvider: CookieJarProvider?,
                       userAgent: String?,
                       withLogs: Boolean
) : BaseApi(
        rootUrl, requestSigner, tfaCallback, cookieJarProvider,
        userAgent, withLogs
) {
    open val accounts: AccountsApiV3 by lazy {
        AccountsApiV3(getService(AccountsServiceV3::class.java))
    }

    open val assetPairs: AssetPairsApi by lazy {
        AssetPairsApi(getService(AssetPairsService::class.java))
    }

    open val assets: AssetsApiV3 by lazy {
        AssetsApiV3(getService(AssetsServiceV3::class.java))
    }

    open val balances: BalancesApi by lazy {
        BalancesApi(getService(BalancesService::class.java))
    }

    open val fees: FeesApiV3 by lazy {
        FeesApiV3(getService(FeesServiceV3::class.java))
    }

    open val keyValue: KeyValueStorageApiV3 by lazy {
        KeyValueStorageApiV3(getService(KeyValueStorageServiceV3::class.java))
    }

    open val offers: OffersApiV3 by lazy {
        OffersApiV3(getService(OffersServiceV3::class.java))
    }

    open val transactions: TransactionsApiV3 by lazy {
        TransactionsApiV3(getService(TransactionsServiceV3::class.java))
    }

    open val requests: RequestsApiV3 by lazy {
        RequestsApiV3(getService(RequestsServiceV3::class.java))
    }

    open val sales: SalesApiV3 by lazy {
        SalesApiV3(getService(SalesServiceV3::class.java))
    }

    open val history: HistoryApi by lazy {
        HistoryApi(getService(HistoryService::class.java))
    }

    open val orderBooks: OrderBooksApi by lazy {
        OrderBooksApi(getService(OrderBooksService::class.java))
    }

    open val signers: SignersApiV3 by lazy {
        SignersApiV3(getService(SignersServiceV3::class.java))
    }

    open val polls: PollsApi by lazy {
        PollsApi(getService(PollsService::class.java))
    }
}