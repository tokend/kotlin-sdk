package org.tokend.sdk.api.requests.model.withdrawal

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.requests.model.base.ReviewableRequestDetails

class WithdrawalReviewableRequestDetails(
        typeI: Int,
        @SerializedName("withdraw")
        val withdrawal: WithdrawalDetails?,
        @SerializedName("two_step_withdrawal")
        val twoStepWithdrawal: WithdrawalDetails?

) : ReviewableRequestDetails(typeI)