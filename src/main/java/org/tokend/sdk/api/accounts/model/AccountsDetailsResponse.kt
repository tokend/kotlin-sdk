package org.tokend.sdk.api.accounts.model

import com.google.gson.annotations.SerializedName

open class AccountsDetailsResponse(
        @SerializedName("users") val users: Map<String, AccountDetails>?) {
    class AccountDetails(@SerializedName("user_type") val userType: String?,
                         @SerializedName("user_state") val userState: String?,
                         @SerializedName("email") val email: String?)
}