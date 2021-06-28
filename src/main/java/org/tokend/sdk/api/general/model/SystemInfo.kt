package org.tokend.sdk.api.general.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.general.model.SystemInfo.Companion.LEDGER_CORE
import org.tokend.sdk.api.general.model.SystemInfo.Companion.LEDGER_HISTORY
import org.tokend.sdk.api.general.model.SystemInfo.Companion.LEDGER_HISTORY_2
import org.tokend.wallet.NetworkParams
import java.util.*

open class SystemInfo(
        /**
         * @see LEDGER_CORE
         * @see LEDGER_HISTORY
         * @see LEDGER_HISTORY_2
         */
        @SerializedName("ledgers_state")
        val ledgersState: Map<String, LedgerState>,
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
    open class LedgerState(
            @SerializedName("latest")
            val latest: Long,
            @SerializedName("oldest_on_start")
            val oldestOnStart: Long,
            @SerializedName("last_ledger_increase_time")
            val lastLedgerIncreaseTime: Date
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SystemInfo

        if (passphrase != other.passphrase) return false
        if (adminAccountId != other.adminAccountId) return false
        if (masterExchangeName != other.masterExchangeName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = passphrase.hashCode()
        result = 31 * result + adminAccountId.hashCode()
        result = 31 * result + masterExchangeName.hashCode()
        return result
    }

    fun toNetworkParams(): NetworkParams {
        val precision = Math.log10(precisionMultiplier.toDouble()).toInt()
        return NetworkParams(passphrase, precision)
    }

    companion object {
        const val LEDGER_CORE = "core"
        const val LEDGER_HISTORY = "history"
        const val LEDGER_HISTORY_2 = "history_2"
    }
}