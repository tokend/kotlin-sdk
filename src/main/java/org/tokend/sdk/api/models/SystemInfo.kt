package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Oleg Koretsky on 3/8/18.
 */
data class SystemInfo(@SerializedName("network_passphrase")
                 val passphrase: String? = null,
                 @SerializedName("commission_account_id")
                 val comissionAccountId: String? = null,
                 @SerializedName("operational_account_id")
                 val operationalAccountId: String? = null,
                 @SerializedName("master_account_id")
                 val masterExchangeAccountId: String? = null)