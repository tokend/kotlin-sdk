package org.tokend.sdk.api.requests.model.base

import com.google.gson.annotations.SerializedName
import org.tokend.wallet.xdr.ReviewableRequestType

/**
 * Base reviewable request details holder.
 */
open class ReviewableRequestDetails(
        @SerializedName("request_type_i")
        val typeI: Int
) {
    open val type: ReviewableRequestType
        get() = ReviewableRequestType.values().find { it.value == typeI }!!
}