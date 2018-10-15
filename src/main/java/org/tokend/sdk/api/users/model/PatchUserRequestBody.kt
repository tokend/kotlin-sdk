package org.tokend.sdk.api.users.model

import com.google.gson.annotations.SerializedName

open class PatchUserRequestBody(@SerializedName("airdrop_state") val airdropState: String)