package org.tokend.sdk.api.v2

import org.tokend.sdk.api.base.BaseApi
import org.tokend.sdk.api.v2.accounts.AccountsApiV2
import org.tokend.sdk.api.v2.accounts.AccountsServiceV2
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
}