package org.tokend.sdk.api.requests.models

import com.google.gson.annotations.SerializedName

data class AccountsDetailsRequestBody(@SerializedName("addresses") val addresses: List<String>)