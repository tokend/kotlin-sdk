package org.tokend.sdk.keyserver.models

import org.tokend.wallet.Account

data class WalletCreationResult(
    val dataToSave: WalletSaveData,
    val accounts: List<Account>,
    val loginParams: LoginParams,
    val isVerified: Boolean
) {
    val walletId: String
        get() = dataToSave.walletId

    val accountId: String
        get() = dataToSave.accountId

    val login: String
        get() = dataToSave.email
}