package org.tokend.sdk.api.requests.model.issuance

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.requests.model.base.ReviewableRequestDetails

class IssuanceReviewableRequestDetails(
        typeI: Int,
        @SerializedName("issuance_create")
        val issuanceCreate: IssuanceCreateDetails

) : ReviewableRequestDetails(typeI)