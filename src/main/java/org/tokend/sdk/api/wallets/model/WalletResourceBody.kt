package org.tokend.sdk.api.wallets.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.keyserver.models.WalletData

class WalletResourceBody(
        @SerializedName("data")
        val data: WalletData
) {
    @SerializedName("included")
    val included = data.relationships.values.map { it.data }
}