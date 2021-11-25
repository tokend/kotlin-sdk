package org.tokend.sdk.test

import org.tokend.sdk.api.TokenDApi
import org.tokend.sdk.signing.AccountRequestSigner
import org.tokend.sdk.tfa.TfaCallback
import org.tokend.wallet.Account

object Util {
    fun getApi(url: String = Config.API_URL,
               tfaCallback: TfaCallback? = null): TokenDApi {
        return TokenDApi(url, tfaCallback = tfaCallback)
    }

    fun getSignedApi(account: Account = Config.ADMIN_ACCOUNT,
                     url: String = Config.API_URL,
                     tfaCallback: TfaCallback? = null): TokenDApi {
        return TokenDApi(url, AccountRequestSigner(account), tfaCallback)
    }
}