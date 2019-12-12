package org.tokend.sdk.api.ingester.requests.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.ingester.requests.model.RequestState
import org.tokend.sdk.api.v3.base.PageQueryParams

/**
 * @see [IngesterRequestParams.Includes]
 */
open class IngesterRequestsPageParams(
        val requestor: String? = null,
        val reviewer: String? = null,
        val state: RequestState? = null,
        val type: Int? = null,
        val pendingTasks: Long? = null,
        val pendingTasksNotSet: Long? = null,
        val pendingTasksAnyOf: Long? = null,
        pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> = super.map().toMutableMap().apply {
        requestor?.also { putFilter("requestor", it) }
        reviewer?.also { putFilter("reviewer", it) }
        state?.also { putFilter("state", it.value) }
        type?.also { putFilter("type", it) }
        pendingTasks?.also { putFilter("pending_tasks", it) }
        pendingTasksNotSet?.also { putFilter("pending_tasks_not_set", it) }
        pendingTasksAnyOf?.also { putFilter("pending_tasks_any_of", it) }
    }

    open class Builder: PageQueryParams.Builder() {
        protected open var requestor: kotlin.String? = null
        protected open var reviewer: kotlin.String? = null
        protected open var state: RequestState? = null
        protected open var type: kotlin.Int? = null
        protected open var pendingTasks: kotlin.Long? = null
        protected open var pendingTasksNotSet: kotlin.Long? = null
        protected open var pendingTasksAnyOf: kotlin.Long? = null

        open fun withRequestor(requestor: kotlin.String) = also { this.requestor = requestor }

        open fun withReviewer(reviewer: kotlin.String) = also { this.reviewer = reviewer }

        open fun withState(state: RequestState) = also { this.state = state }

        open fun withType(type: kotlin.Int) = also { this.type = type }

        open fun withPendingTasks(pendingTasks: kotlin.Long) = also { this.pendingTasks = pendingTasks }

        open fun withPendingTasksNotSet(pendingTasksNotSet: kotlin.Long) = also { this.pendingTasksNotSet = pendingTasksNotSet }

        open fun withPendingTasksAnyOf(pendingTasksAnyOf: kotlin.Long) = also { this.pendingTasksAnyOf = pendingTasksAnyOf }

        override fun withInclude(include: Collection<String>?) = also { super.withInclude(include) }

        override fun withInclude(vararg include: String) = also { super.withInclude(*include) }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also { super.withPagingParams(pagingParams) }

        override fun build(): IngesterRequestsPageParams =
                IngesterRequestsPageParams(requestor, reviewer, state, type, pendingTasks, pendingTasksNotSet, pendingTasksAnyOf, pagingParams, include)
    }
}