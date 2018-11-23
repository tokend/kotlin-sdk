package org.tokend.sdk.api.requests.model.sale

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.requests.model.base.ReviewableRequestDetails

class SaleReviewableRequestDetails<SaleType>(
        typeI: Int,
        @SerializedName("sale")
        val sale: SaleType
) : ReviewableRequestDetails(typeI)