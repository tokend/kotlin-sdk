package org.tokend.sdk.signing

import org.tokend.wallet.Account

/**
 * Request signer to account.
 * @param [account] must be active signer.
 * @see <a href="https://tokend.gitbook.io/knowledge-base/technical-details/key-entities/accounts#signers">Knowledge base</a>
 */
class AccountRequestSigner(
        private val account: Account
) : RequestSigner {
    override val accountId = account.accountId

    override fun signToBase64(data: ByteArray): String {
        return account.signDecorated(data).toBase64()
    }
}