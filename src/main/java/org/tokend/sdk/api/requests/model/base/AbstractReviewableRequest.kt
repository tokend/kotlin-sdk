package org.tokend.sdk.api.requests.model.base

import com.google.gson.annotations.SerializedName
import java.util.*

abstract class AbstractReviewableRequest(
        @SerializedName("id")
        val id: Long,
        @SerializedName("paging_token")
        val pagingToken: String,
        @SerializedName("requestor")
        val requestor: String,
        @SerializedName("reviewer")
        val reviewer: String,
        @SerializedName("reference")
        val reference: String?,
        @SerializedName("reject_reason")
        val rejectReason: String,
        @SerializedName("hash")
        val hash: String,
        @SerializedName("created_at")
        val createdAt: Date,
        @SerializedName("updated_at")
        val updatedAt: Date,
        @SerializedName("request_state_i")
        val stateI: Int,
        @SerializedName("all_tasks")
        val allTasks: Int = 0,
        @SerializedName("pending_tasks")
        val pendingTasks: Int = 0,
        @SerializedName("type")
        val type: Long = 0
) {
    abstract val details: ReviewableRequestDetails

    open val state: RequestState
        get() = RequestState.fromI(stateI)
}