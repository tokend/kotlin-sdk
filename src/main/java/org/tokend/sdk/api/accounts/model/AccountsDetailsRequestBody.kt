package org.tokend.sdk.api.accounts.model

import com.google.gson.annotations.SerializedName

open class AccountsDetailsRequestBody(@SerializedName("addresses") val addresses: Collection<String>)