package org.tokend.sdk.api.requests.model.issuance

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.requests.model.base.ReviewableRequestDetails

class IssuanceReviewableRequestDetails(
        typeI: Int,
        @SerializedName("create_issuance")
        val issuanceCreate: IssuanceCreateDetails

) : ReviewableRequestDetails(typeI)