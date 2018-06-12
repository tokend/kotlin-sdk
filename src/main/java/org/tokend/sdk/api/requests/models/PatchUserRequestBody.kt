package org.tokend.sdk.api.requests.models

import com.google.gson.annotations.SerializedName

open class PatchUserRequestBody(@SerializedName("airdrop_state") val airdropState: String)