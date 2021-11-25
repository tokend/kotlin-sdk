package org.tokend.sdk.keyserver.models


class DecryptedWallet(
    var accountId: String,
    var login: String,
    var walletId: String,
    val secretSeeds: List<CharArray>,
    var loginParams: LoginParams
) {
    fun copy(
        accountId: String = this.accountId,
        login: String = this.login,
        walletIdHex: String = this.walletId,
        secretSeeds: List<CharArray> = this.secretSeeds,
        loginParams: LoginParams = this.loginParams
    ) = DecryptedWallet(accountId, login, walletIdHex, secretSeeds, loginParams)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DecryptedWallet

        if (accountId != other.accountId) return false
        if (login != other.login) return false
        if (walletId != other.walletId) return false
        val otherSeeds = other.secretSeeds
        secretSeeds.forEachIndexed { i, seed ->
            if (!otherSeeds[i].contentEquals(seed)) {
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
        result = 31 * result + secretSeeds.hashCode()
        result = 31 * result + loginParams.hashCode()
        return result
    }
}