package org.tokend.sdk.keyserver.models

import org.tokend.wallet.Account


class DecryptedWallet(
    var accountId: String,
    var login: String,
    var walletId: String,
    val accounts: List<Account>,
    var loginParams: LoginParams
) {
    fun copy(
        accountId: String = this.accountId,
        login: String = this.login,
        walletIdHex: String = this.walletId,
        accounts: List<Account> = this.accounts,
        loginParams: LoginParams = this.loginParams
    ) = DecryptedWallet(accountId, login, walletIdHex, accounts, loginParams)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DecryptedWallet

        if (accountId != other.accountId) return false
        if (login != other.login) return false
        if (walletId != other.walletId) return false
        val otherAccounts = other.accounts
        accounts.forEachIndexed { i, account ->
            if (!otherAccounts[i].publicKey.contentEquals(account.publicKey)) {
                return false
            }
        }
        if (loginParams != other.loginParams) return false

        return true
    }

    override fun hashCode(): Int {
        var result = accountId.hashCode()
        result = 31 * result + login.hashCode()
        result = 31 * result + walletId.hashCode()
        result = 31 * result + accounts.hashCode()
        result = 31 * result + loginParams.hashCode()
        return result
    }
}