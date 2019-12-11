package org.tokend.sdk.signing

import org.tokend.sdk.utils.extentions.encodeBase64String
import org.tokend.wallet.Account

/**
 * Request signer based on given [Account].
 *
 * @param [accountId] ID of signer's account (not signer public key)
 * @param [signerAccount] must be active signer.
 *
 * @see <a href="https://tokend.gitbook.io/knowledge-base/technical-details/key-entities/accounts#signers">Knowledge base</a>
 * @see <a href="https://tokend.gitbook.io/knowledge-base/technical-details/security#rest-api">Requests signing on Knowledge base</a>
 */
class AccountRequestSigner(
        override val accountId: String,
        private val signerAccount: Account
) : RequestSigner {
    override val keyId = signerAccount.accountId

    override fun signToBase64(data: ByteArray): String {
        return signerAccount.sign(data).encodeBase64String()
    }
}