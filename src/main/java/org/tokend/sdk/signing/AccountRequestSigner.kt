package org.tokend.sdk.signing

import org.tokend.wallet.Account

class AccountRequestSigner(
        private val account: Account
) : RequestSigner {
    override val accountId = account.accountId

    override fun signToBase64(data: ByteArray): String {
        return account.signDecorated(data).toBase64()
    }
}