package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Oleg Koretsky on 1/4/18.
 */
open class AccountsDetailsResponse(
        @SerializedName("users") val users: Map<String, AccountDetails>?) {
    class AccountDetails(@SerializedName("user_type") val userType: String?,
                         @SerializedName("user_state") val userState: String?,
                         @SerializedName("email") val email: String?)
}