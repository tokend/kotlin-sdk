package org.tokend.sdk.keyserver.models


class WalletInfo(
    var accountId: String,
    var email: String,
    var walletIdHex: String,
    secretSeeds: List<CharArray>?,
    var loginParams: LoginParams
) {
    constructor(
        accountId: String,
        email: String,
        walletIdHex: String,
        secretSeed: CharArray,
        loginParams: LoginParams
    ) : this(accountId, email, walletIdHex, listOf(secretSeed), loginParams)

    private var mSecretSeeds: List<CharArray>? = secretSeeds

    private val legacySingleSecretSeed: CharArray? = null

    var secretSeeds: List<CharArray>
        get() = mSecretSeeds
            ?: legacySingleSecretSeed?.let { listOf(it) }
            ?: throw IllegalStateException("No secret seeds found")
        set(value) {
            mSecretSeeds = value
        }

    var secretSeed: CharArray
        get() = secretSeeds.first()
        set(value) {
            secretSeeds = listOf(value)
        }

    fun copy(
        accountId: String = this.accountId,
        email: String = this.email,
        walletIdHex: String = this.walletIdHex,
        secretSeeds: List<CharArray>? = this.secretSeeds,
        loginParams: LoginParams = this.loginParams
    ) = WalletInfo(accountId, email, walletIdHex, secretSeeds, loginParams)

    fun copy(
        accountId: String = this.accountId,
        email: String = this.email,
        walletIdHex: String = this.walletIdHex,
        secretSeed: CharArray = this.secretSeed,
        loginParams: LoginParams = this.loginParams
    ) = WalletInfo(accountId, email, walletIdHex, listOf(secretSeed), loginParams)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is WalletInfo) return false

        if (accountId != other.accountId) return false
        if (email != other.email) return false
        if (walletIdHex != other.walletIdHex) return false
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
        result = 31 * result + email.hashCode()
        result = 31 * result + walletIdHex.hashCode()
        result = 31 * result + secretSeeds.hashCode()
        result = 31 * result + loginParams.hashCode()
        return result
    }
}