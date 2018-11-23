package org.tokend.sdk.api.requests.model.kyc

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.requests.model.base.ReviewableRequestDetails

class KycUpdateReviewableRequestDetails(
        typeI: Int,
        @SerializedName("update_kyc")
        val updateKyc: KycUpdateDetails
) : ReviewableRequestDetails(typeI)