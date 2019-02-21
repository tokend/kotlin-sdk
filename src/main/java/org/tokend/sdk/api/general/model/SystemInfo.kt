package org.tokend.sdk.api.general.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.HashCodes
import org.tokend.wallet.NetworkParams

open class SystemInfo(
        @SerializedName("network_passphrase")
        val passphrase: String,
        @SerializedName("current_time")
        val currentTime: Long,
        @SerializedName("commission_account_id")
        val commissionAccountId: String,
        @SerializedName("operational_account_id")
        val operationalAccountId: String,
        @SerializedName("admin_account_id")
        val adminAccountId: String,
        @SerializedName("master_exchange_name")
        val masterExchangeName: String,
        @SerializedName("precision")
        val precisionMultiplier: Long
) {
    override fun equals(other: Any?): Boolean {
        return other is SystemInfo
                && other.passphrase == passphrase
                && other.masterExchangeName == masterExchangeName
                && other.adminAccountId == adminAccountId
    }

    override fun hashCode(): Int {
        return HashCodes.ofMany(passphrase, masterExchangeName, adminAccountId)
    }

    fun toNetworkParams(): NetworkParams {
        val precision = Math.log10(precisionMultiplier.toDouble()).toInt()
        return NetworkParams(passphrase, precision)
    }
}