package org.tokend.sdk.api.wallets.model

import com.google.gson.annotations.SerializedName

open class VerifyWalletRequestBody(@SerializedName("token") val token: String)