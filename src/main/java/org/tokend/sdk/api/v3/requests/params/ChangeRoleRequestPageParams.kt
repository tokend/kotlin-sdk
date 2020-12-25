package org.tokend.sdk.api.v3.requests.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.requests.model.RequestState
import org.tokend.wallet.xdr.ReviewableRequestType

/**
 * @see ChangeRoleRequestPageParams.Includes
 */
class ChangeRoleRequestPageParams(
        reviewer: String? = null,
        requestor: String? = null,
        state: RequestState? = null,
        pendingTasks: Long? = null,
        pendingTasksNotSet: Long? = null,
        pendingTasksAnyOf: Long? = null,
        missingPendingTasks: Long? = null,
        val destinationAccount: String? = null,
        val accountRoleToSet: Long? = null,
        includes: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : RequestsPageParamsV3(reviewer, requestor, state, ReviewableRequestType.CHANGE_ROLE,
        pendingTasks, pendingTasksNotSet, pendingTasksAnyOf, missingPendingTasks,
        includes, pagingParams) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            destinationAccount?.also { putFilter("request_details.destination_account", it) }
            accountRoleToSet?.also { putFilter("request_details.account_role_to_set", it) }
        }
    }

    open class Builder : RequestsPageParamsV3.Builder() {
        protected var destinationAccount: String? = null
        protected var accountRoleToSet: Long? = null

        open fun withDestinationAccount(destinationAccount: String) = also { this.destinationAccount = destinationAccount }

        open fun withAccountRoleToSet(accountRoleToSet: Long) = also { this.accountRoleToSet = accountRoleToSet }

        override fun withReviewer(reviewer: String) = also { super.withReviewer(reviewer) }

        override fun withRequestor(requestor: String) = also { super.withRequestor(requestor) }

        override fun withState(state: RequestState) = also { super.withState(state) }

        override fun withType(type: ReviewableRequestType) =
                throw NotImplementedError("Type is already defined by the request you are trying to perform with this params")

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

        override fun build(): ChangeRoleRequestPageParams {
            return ChangeRoleRequestPageParams(reviewer, requestor, state, pendingTasks,
                    pendingTasksNotSet, pendingTasksAnyOf, missingPendingTasks,
                    destinationAccount, accountRoleToSet, include, pagingParams)
        }
    }

    class Includes {
        companion object {
            const val DESTINATION_ACCOUNT = "request_details.account"
        }
    }
}