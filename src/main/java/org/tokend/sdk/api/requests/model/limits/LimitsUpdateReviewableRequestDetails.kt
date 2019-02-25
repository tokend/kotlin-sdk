package org.tokend.sdk.api.requests.model.limits

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.requests.model.base.ReviewableRequestDetails

class LimitsUpdateReviewableRequestDetails(
        typeI: Int,
        @SerializedName("update_limits")
        val limitsUpdate: LimitsUpdateDetailsHolder

) : ReviewableRequestDetails(typeI)