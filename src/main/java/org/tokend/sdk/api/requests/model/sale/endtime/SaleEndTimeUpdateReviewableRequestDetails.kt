package org.tokend.sdk.api.requests.model.sale.endtime

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.requests.model.base.ReviewableRequestDetails

class SaleEndTimeUpdateReviewableRequestDetails(
        typeI: Int,
        @SerializedName("update_sale_end_time")
        val updateEndTime: SaleEndTimeUpdateDetails
) : ReviewableRequestDetails(typeI)