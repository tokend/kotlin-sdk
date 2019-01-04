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
}