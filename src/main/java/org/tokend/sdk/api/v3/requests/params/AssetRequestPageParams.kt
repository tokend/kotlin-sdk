package org.tokend.sdk.api.v3.requests.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.requests.model.base.RequestState
import org.tokend.wallet.xdr.ReviewableRequestType

open class AssetRequestPageParams(
        reviewer: String? = null,
        requestor: String? = null,
        state: RequestState? = null,
        type: ReviewableRequestType? = null,
        updatedAfter: Long? = null,
        includes: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null,
        val asset: String? = null
) : RequestsPageParamsV3(
        reviewer,
        requestor,
        state,
        type,
        updatedAfter,
        includes,
        pagingParams) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            asset?.also { putFilter("asset", it) }
        }
    }

    class Builder : RequestsPageParamsV3.Builder() {
        private var asset: String? = null

        fun withAsset(asset: String) = also { this.asset = asset }

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
            return AssetRequestPageParams(reviewer, requestor, state, type, updatedAfter, include, pagingParams, asset)
        }
    }
}