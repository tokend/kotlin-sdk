package org.tokend.sdk.api.v2.requests.params

import org.tokend.sdk.api.base.params.PagingParamsHolder
import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.requests.model.base.RequestState
import org.tokend.sdk.api.v2.base.JsonApiQueryParams
import org.tokend.wallet.xdr.ReviewableRequestType

open class RequestsPageParamsV2(
        val reviewer: String? = null,
        val requestor: String? = null,
        val state: RequestState? = null,
        val type: ReviewableRequestType? = null,
        val updatedAfter: Long? = null,
        includes: Collection<String>? = null,
        val pagingParams: PagingParamsV2? = null
) : RequestParamsV2(includes), PagingParamsHolder {
    override val order = pagingParams?.order
    override val cursor = pagingParams?.cursor
    override val limit = pagingParams?.limit

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            reviewer?.also { put("reviewer", it) }
            requestor?.also { put("requestor", it) }
            state?.also { put("state_i", it.i) }
            type?.also { put("type", it.value) }
            updatedAfter?.also { put("updated_after", it) }
            pagingParams?.also { putAll(it.map()) }
        }
    }

    open class Builder : JsonApiQueryParams.Builder() {
        protected var reviewer: String? = null
        protected var requestor: String? = null
        protected var state: RequestState? = null
        protected var type: ReviewableRequestType? = null
        protected var updatedAfter: Long? = null
        protected var pagingParams: PagingParamsV2? = null

        fun withReviewer(reviewer: String) = also { this.reviewer = reviewer }

        fun withRequestor(requestor: String) = also { this.requestor = requestor }

        fun withState(state: RequestState) = also { this.state = state }

        fun withType(type: ReviewableRequestType) = also { this.type = type }

        fun withUpdateAfter(updateAfter: Long) = also { this.updatedAfter = updateAfter }

        fun withPagingParams(pagingParams: PagingParamsV2) = also { this.pagingParams = pagingParams }

        override fun build(): JsonApiQueryParams {
            return RequestsPageParamsV2(reviewer, requestor, state, type, updatedAfter, include, pagingParams)
        }
    }
}