package org.tokend.sdk.api.responses

import com.google.gson.annotations.SerializedName
import java.util.*

class UserInfo(@SerializedName("type")
               val type: String? = null,
               @SerializedName("id")
               val id: String? = null,
               @SerializedName("attributes")
               val attributes: Attributes? = null) {

    class Attributes(@SerializedName("email")
                     val email: String? = null,
                     @SerializedName("state")
                     val state: String? = null,
                     @SerializedName("kyc_sequence")
                     val kycSequence: Int? = null,
                     @SerializedName("reject_reason")
                     val rejectReason: String? = null,
                     @SerializedName("recovery_address")
                     val recoveryAddress: String? = null,
                     @SerializedName("airdrop_state")
                     val airdropState: String? = null,
                     @SerializedName("created_at")
                     val createdAt: Date? = null)

    companion object {
        val STATE_NEED_DOCUMENTS = "need_documents"
        val STATE_WAITING_FOR_APPROVAL = "waiting_for_approval"
        val STATE_APPROVED = "approved"
        val STATE_REJECTED = "rejected"
        val STATE_BLOCKED = "blocked"
        val AIRDROP_STATE_CLAIMED = "claimed"
        val AIRDROP_STATE_ELIGIBLE = "eligible"
    }
}
