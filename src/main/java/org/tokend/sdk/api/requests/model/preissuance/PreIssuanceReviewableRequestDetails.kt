package org.tokend.sdk.api.requests.model.preissuance

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.requests.model.base.ReviewableRequestDetails

class PreIssuanceReviewableRequestDetails(
        typeI: Int,
        @SerializedName("pre_issuance_create")
        val preIssuanceCreate: PreIssuanceCreateDetails

) : ReviewableRequestDetails(typeI) {
}