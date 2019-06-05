package org.tokend.sdk.api.v3.polls.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams
import org.tokend.sdk.api.v3.polls.model.PollState
import org.tokend.sdk.utils.ApiDateUtil
import org.tokend.wallet.xdr.PollType
import java.util.*

open class PollsPageParams(
        val owner: String? = null,
        val permissionType: Long? = null,
        val pollType: PollType? = null,
        val voteConfirmation: Boolean? = null,
        val minStartTime: Date? = null,
        val minEndTime: Date? = null,
        val maxStartTime: Date? = null,
        val maxEndTime: Date? = null,
        val state: PollState? = null,
        include: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, include) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            owner?.also { putFilter("owner", it) }
            permissionType?.also { putFilter("permission_type", it) }
            pollType?.also { putFilter("poll_type", it.value) }
            voteConfirmation?.also { putFilter("vote_confirmation", it) }
            minStartTime?.also { putFilter("min_start_time", ApiDateUtil.formatDateForRequest(it)) }
            minEndTime?.also { putFilter("min_end_time", ApiDateUtil.formatDateForRequest(it)) }
            maxStartTime?.also { putFilter("max_start_time", ApiDateUtil.formatDateForRequest(it)) }
            maxEndTime?.also { putFilter("max_end_time", ApiDateUtil.formatDateForRequest(it)) }
            state?.also { putFilter("state", it.value) }
        }
    }

    class Builder : PageQueryParams.Builder() {
        private var owner: String? = null
        private var permissionType: Long? = null
        private var pollType: PollType? = null
        private var voteConfirmation: Boolean? = null
        private var minStartTime: Date? = null
        private var minEndTime: Date? = null
        private var maxStartTime: Date? = null
        private var maxEndTime: Date? = null
        private var state: PollState? = null

        fun withOwner(owner: String) = apply { this.owner = owner }

        fun withPermissionType(permissionType: Long) = apply { this.permissionType = permissionType }

        fun withPollType(pollType: PollType) = apply { this.pollType = pollType }

        fun withVoteConfirmation(voteConfirmation: Boolean) = apply { this.voteConfirmation = voteConfirmation }

        fun withMinStartTime(minStartTime: Date) = apply { this.minStartTime = minStartTime }

        fun withMinEndTime(minEndTime: Date) = apply { this.minEndTime = minEndTime }

        fun withMaxStartTime(maxStartTime: Date) = apply { this.maxStartTime = maxStartTime }

        fun withMaxEndTime(maxEndTime: Date) = apply { this.maxEndTime = maxEndTime }

        fun withState(state: PollState) = apply { this.state = state }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also {
            super.withPagingParams(pagingParams)
        }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): PollsPageParams {
            return PollsPageParams(owner, permissionType, pollType, voteConfirmation,
                    minStartTime, minEndTime, maxStartTime, maxEndTime, state,
                    include, pagingParams)
        }
    }
}