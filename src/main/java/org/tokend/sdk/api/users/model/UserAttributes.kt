package org.tokend.sdk.api.users.model

import com.google.gson.annotations.SerializedName
import java.util.*

open class UserAttributes(
        @SerializedName("email")
        open val email: String,
        @SerializedName("state")
        open val state: String,
        @SerializedName("created_at")
        open val createdAt: Date,
        @SerializedName("updated_at")
        open val updatedAt: Date,
        @SerializedName("airdrop_state")
        open val airdropState: String
) {
    companion object {
        const val STATE_NEED_DOCUMENTS = "need_documents"
        const val STATE_WAITING_FOR_APPROVAL = "waiting_for_approval"
        const val STATE_APPROVED = "approved"
        const val STATE_REJECTED = "rejected"
        const val STATE_BLOCKED = "blocked"
    }
}