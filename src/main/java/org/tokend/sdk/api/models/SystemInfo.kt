package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName

open class SystemInfo(
        @SerializedName("network_passphrase")
        val passphrase: String,
        @SerializedName("current_time")
        val currentTime: Long,
        @SerializedName("commission_account_id")
        val commissionAccountId: String,
        @SerializedName("operational_account_id")
        val operationalAccountId: String,
        @SerializedName("master_account_id")
        val masterExchangeAccountId: String,
        @SerializedName("master_exchange_name")
        val masterExchangeName: String
)