package org.tokend.sdk.keyserver.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class WalletInfo(
        @SerializedName("accountId")
        var accountId: String,
        @SerializedName("email")
        var email: String,
        @SerializedName("walletIdHex")
        var walletIdHex: String,
        @SerializedName("secretSeed")
        var secretSeed: CharArray,
        @SerializedName("loginParams")
        var loginParams: LoginParams) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as WalletInfo

                if (accountId != other.accountId) return false
                if (email != other.email) return false
                if (walletIdHex != other.walletIdHex) return false
                if (!Arrays.equals(secretSeed, other.secretSeed)) return false
                if (loginParams != other.loginParams) return false

                return true
        }

        override fun hashCode(): Int {
                var result = accountId.hashCode()
                result = 31 * result + email.hashCode()
                result = 31 * result + walletIdHex.hashCode()
                result = 31 * result + Arrays.hashCode(secretSeed)
                result = 31 * result + loginParams.hashCode()
                return result
        }
}