package org.tokend.sdk.api.v3.requests.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.requests.model.base.RequestState
import org.tokend.sdk.api.v3.base.PageQueryParams
import org.tokend.wallet.xdr.ReviewableRequestType

open class RequestsPageParamsV3(
        val reviewer: String? = null,
        val requestor: String? = null,
        val state: RequestState? = null,
        val type: ReviewableRequestType? = null,
        val pendingTasks: Long? = null,
        val pendingTasksNotSet: Long? = null,
        val pendingTasksAnyOf: Long? = null,
        val missingPendingTasks: Long? = null,
        includes: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, includes) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            reviewer?.also { putFilter("reviewer", it) }
            requestor?.also { putFilter("requestor", it) }
            state?.also { putFilter("state", it.i) }
            type?.also { putFilter("type", it.value) }
            pendingTasks?.also { putFilter("pending_tasks", it) }
            pendingTasksNotSet?.also { putFilter("pending_tasks_not_set", it) }
            pendingTasksAnyOf?.also { putFilter("pending_tasks_any_of", it) }
            missingPendingTasks?.also { putFilter("missing_pending_tasks", it) }
        }
    }

    open class Builder : PageQueryParams.Builder() {
        protected var reviewer: String? = null
        protected var requestor: String? = null
        protected var state: RequestState? = null
        protected var type: ReviewableRequestType? = null
        protected var pendingTasks: Long? = null
        protected var pendingTasksNotSet: Long? = null
        protected var pendingTasksAnyOf: Long? = null
        protected var missingPendingTasks: Long? = null

        open fun withReviewer(reviewer: String) = also { this.reviewer = reviewer }

        open fun withRequestor(requestor: String) = also { this.requestor = requestor }

        open fun withState(state: RequestState) = also { this.state = state }

        open fun withType(type: ReviewableRequestType) = also { this.type = type }

        open fun withPendingTasks(pendingTasks: Long) = also { this.pendingTasks = pendingTasks }

        open fun withPendingTasksNotSet(pendingTasksNotSet: Long) = also { this.pendingTasksNotSet = pendingTasksNotSet }

        open fun withPendingTasksAnyOf(pendingTasksAnyOf: Long) = also { this.pendingTasksAnyOf = pendingTasksAnyOf }

        open fun withMissingPendingTasks(missingPendingTasks: Long) = also { this.missingPendingTasks = missingPendingTasks }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also {
            super.withPagingParams(pagingParams)
        }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): RequestsPageParamsV3 {
            return RequestsPageParamsV3(reviewer, requestor, state, type,
                    pendingTasks, pendingTasksNotSet, pendingTasksAnyOf, missingPendingTasks,
                    include, pagingParams)
        }
    }
}