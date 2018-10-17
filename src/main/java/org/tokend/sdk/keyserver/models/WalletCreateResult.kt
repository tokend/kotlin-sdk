package org.tokend.sdk.keyserver.models

import org.tokend.wallet.Account

data class WalletCreateResult(
        val walletData: WalletData,
        val rootAccount: Account,
        val recoveryAccount: Account
)