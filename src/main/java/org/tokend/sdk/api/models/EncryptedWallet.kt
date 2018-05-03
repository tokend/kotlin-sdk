package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.extentions.decodeBase64
import org.tokend.sdk.utils.extentions.encodeHexString

class EncryptedWallet(
        @SerializedName("username")
        var username: String,
        @SerializedName("walletId")
        var walletIdBase64: String,
        @SerializedName("salt")
        var salt: String,
        @SerializedName("publicKey")
        var publicKey: String,
        @SerializedName("mainData")
        var mainData: String,
        @SerializedName("mainDataHash")
        var mainDataHash: String,
        @SerializedName("keychainData")
        var keychainData: String,
        @SerializedName("keychainDataHash")
        var keychainDataHash: String,
        @SerializedName("kdfParams")
        var kdfParams: String
) {
    val walletIdHex: String
        get() = walletIdBase64.decodeBase64().encodeHexString().toLowerCase()

    fun setWalletId(walletId: String) {
        this.walletIdBase64 = walletId
    }
}