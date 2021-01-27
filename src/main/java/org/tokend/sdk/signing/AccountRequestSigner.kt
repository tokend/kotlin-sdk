package org.tokend.sdk.signing

import org.tokend.sdk.utils.extentions.encodeBase64String
import org.tokend.wallet.Account

/**
 * Request signer based on given [Account].
 *
 * @param [account] must be active signer.
 *
 * @see <a href="https://tokend.gitbook.io/knowledge-base/technical-details/key-entities/accounts#signers">Knowledge base</a>
 * @see <a href="https://tokend.gitbook.io/knowledge-base/technical-details/security#rest-api">Requests signing on Knowledge base</a>
 */
class AccountRequestSigner
@JvmOverloads
constructor(
        private val account: Account,
        override val originalAccountId: String = ""
) : RequestSigner {
    override val accountId = account.accountId

    override fun signToBase64(data: ByteArray): String {
        return account.sign(data).encodeBase64String()
    }
}