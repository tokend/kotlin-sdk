package org.tokend.sdk.api.ingester

import org.tokend.sdk.api.base.BaseApi
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.custom.CustomRequestsService
import org.tokend.sdk.api.ingester.accounts.IngesterAccountsApi
import org.tokend.sdk.api.ingester.accounts.IngesterAccountsService
import org.tokend.sdk.api.ingester.assets.IngesterAssetsApi
import org.tokend.sdk.api.ingester.balances.IngesterBalancesApi
import org.tokend.sdk.api.ingester.history.IngesterHistoryApi
import org.tokend.sdk.api.ingester.info.IngesterInfoApi
import org.tokend.sdk.api.ingester.keyvalue.IngesterKeyValueApi
import org.tokend.sdk.api.ingester.publickeys.IngesterPublicKeysApi
import org.tokend.sdk.api.ingester.requests.IngesterRequestsApi
import org.tokend.sdk.api.ingester.roles.IngesterRolesApi
import org.tokend.sdk.api.ingester.rules.IngesterRulesApi
import org.tokend.sdk.api.ingester.transactions.IngesterTransactionsApi
import org.tokend.sdk.api.ingester.transactions.IngesterTransactionsService
import org.tokend.sdk.signing.RequestSigner
import org.tokend.sdk.tfa.TfaCallback
import org.tokend.sdk.utils.CookieJarProvider

open class IngesterApi(
        rootUrl: String,
        requestSigner: RequestSigner?,
        tfaCallback: TfaCallback?,
        cookieJarProvider: CookieJarProvider?,
        userAgent: String?, withLogs: Boolean
) : BaseApi(rootUrl, requestSigner, tfaCallback, cookieJarProvider, userAgent, withLogs) {

    protected open val customRequests: CustomRequestsApi by lazy {
        CustomRequestsApi(getService(CustomRequestsService::class.java))
    }

    open val transactions: IngesterTransactionsApi by lazy {
        IngesterTransactionsApi(customRequests, getService(IngesterTransactionsService::class.java))
    }

    open val requests: IngesterRequestsApi by lazy {
        IngesterRequestsApi(customRequests)
    }

    open val accounts: IngesterAccountsApi by lazy {
        IngesterAccountsApi(customRequests, getService(IngesterAccountsService::class.java))
    }

    open val history: IngesterHistoryApi by lazy {
        IngesterHistoryApi(customRequests)
    }

    open val info: IngesterInfoApi by lazy {
        IngesterInfoApi(customRequests)
    }

    open val keyValue: IngesterKeyValueApi by lazy {
        IngesterKeyValueApi(customRequests)
    }

    open val publicKeys: IngesterPublicKeysApi by lazy {
        IngesterPublicKeysApi(customRequests)
    }

    open val roles: IngesterRolesApi by lazy {
        IngesterRolesApi(customRequests)
    }

    open val rules: IngesterRulesApi by lazy {
        IngesterRulesApi(customRequests)
    }

    open val assets: IngesterAssetsApi by lazy {
        IngesterAssetsApi(customRequests)
    }

    open val balances: IngesterBalancesApi by lazy {
        IngesterBalancesApi(customRequests)
    }
}