package org.tokend.sdk.api.requests.model.preissuance

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.requests.model.base.ReviewableRequestDetails

class PreIssuanceReviewableRequestDetails(
        typeI: Int,
        @SerializedName("create_pre_issuance")
        val preIssuanceCreate: PreIssuanceCreateDetails

) : ReviewableRequestDetails(typeI) {
}