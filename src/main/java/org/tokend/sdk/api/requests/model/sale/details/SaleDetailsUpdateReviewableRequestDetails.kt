package org.tokend.sdk.api.requests.model.sale.details

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.requests.model.base.ReviewableRequestDetails

// Isn't it a nice name? I would like to name my baby so.
class SaleDetailsUpdateReviewableRequestDetails(
        typeI: Int,
        @SerializedName("update_sale_details")
        val updateDetails: SaleDetailsUpdateData
) : ReviewableRequestDetails(typeI)