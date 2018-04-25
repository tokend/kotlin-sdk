package org.tokend.sdk.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.extentions.decodeBase64
import org.tokend.sdk.utils.extentions.encodeHexString

class EncryptedWallet {
    @SerializedName("username")
    @Expose
    var username: String? = null
    @SerializedName("walletId")
    @Expose
    var walletIdBase64: String? = null
        private set
    @SerializedName("salt")
    @Expose
    var salt: String? = null
    @SerializedName("publicKey")
    @Expose
    var publicKey: String? = null
    @SerializedName("mainData")
    @Expose
    var mainData: String? = null
    @SerializedName("mainDataHash")
    @Expose
    var mainDataHash: String? = null
    @SerializedName("keychainData")
    @Expose
    var keychainData: String? = null
    @SerializedName("keychainDataHash")
    @Expose
    var keychainDataHash: String? = null
    @SerializedName("kdfParams")
    @Expose
    var kdfParams: String? = null

    val walletIdHex: String
        get() = walletIdBase64!!.decodeBase64().encodeHexString().toLowerCase()

    fun setWalletId(walletId: String) {
        this.walletIdBase64 = walletId
    }

}