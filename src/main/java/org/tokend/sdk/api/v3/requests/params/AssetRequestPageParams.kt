package org.tokend.sdk.api.v3.requests.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.requests.model.RequestState
import org.tokend.wallet.xdr.ReviewableRequestType

/**
 * @see AssetRequestPageParams.Includes
 */
open class AssetRequestPageParams(
        reviewer: String? = null,
        requestor: String? = null,
        state: RequestState? = null,
        type: ReviewableRequestType? = null,
        pendingTasks: Long? = null,
        pendingTasksNotSet: Long? = null,
        pendingTasksAnyOf: Long? = null,
        missingPendingTasks: Long? = null,
        val assetCode: String? = null,
        includes: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : RequestsPageParamsV3(reviewer, requestor, state, type,
        pendingTasks, pendingTasksNotSet, pendingTasksAnyOf, missingPendingTasks,
        includes, pagingParams) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            assetCode?.also { putFilter("request_details.asset", it) }
        }
    }

    open class Builder : RequestsPageParamsV3.Builder() {
        private var assetCode: String? = null

        fun withAssetCode(assetCode: String) = also { this.assetCode = assetCode }

        override fun withReviewer(reviewer: String) = also { super.withReviewer(reviewer) }

        override fun withRequestor(requestor: String) = also { super.withRequestor(requestor) }

        override fun withState(state: RequestState) = also { super.withState(state) }

        override fun withType(type: ReviewableRequestType) = also { super.withType(type) }

        override fun withPendingTasks(pendingTasks: Long) = also { super.withPendingTasks(pendingTasks) }

        override fun withPendingTasksNotSet(pendingTasksNotSet: Long) = also { super.withPendingTasksNotSet(pendingTasksNotSet) }

        override fun withPendingTasksAnyOf(pendingTasksAnyOf: Long) = also { super.withPendingTasksAnyOf(pendingTasksAnyOf) }

        override fun withMissingPendingTasks(missingPendingTasks: Long) = also { super.withMissingPendingTasks(missingPendingTasks) }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also {
            super.withPagingParams(pagingParams)
        }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): AssetRequestPageParams {
            return AssetRequestPageParams(reviewer, requestor, state, type, pendingTasks,
                    pendingTasksNotSet, pendingTasksAnyOf, missingPendingTasks,
                    assetCode, include, pagingParams)
        }
    }

    class Includes {
        companion object {
            const val ASSET = "request_details.asset"
        }
    }
}