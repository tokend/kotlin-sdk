package org.tokend.sdk.api.requests.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.requests.model.base.AbstractReviewableRequest
import org.tokend.sdk.api.requests.model.base.ReviewableRequestDetails
import java.util.*

/**
 * Reviewable request without specific details.
 */
class SimpleReviewableRequest(
        id: Long,
        pagingToken: String,
        requestor: String,
        reviewer: String,
        reference: String?,
        rejectReason: String,
        hash: String,
        createdAt: Date,
        updatedAt: Date,
        stateI: Int,
        @SerializedName("details")
        override val details: ReviewableRequestDetails
) : AbstractReviewableRequest(id, pagingToken, requestor, reviewer, reference,
        rejectReason, hash, createdAt, updatedAt, stateI)