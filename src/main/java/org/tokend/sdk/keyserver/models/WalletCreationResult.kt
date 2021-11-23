package org.tokend.sdk.keyserver.models

import org.tokend.wallet.Account

data class WalletCreationResult(
    val creationData: WalletCreationData,
    val rootAccount: Account,
    val walletId: String,
    val loginParams: LoginParams,
    val accounts: List<Account>,
    val isVerified: Boolean
)