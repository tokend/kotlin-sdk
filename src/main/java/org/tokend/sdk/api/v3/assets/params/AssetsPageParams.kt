package org.tokend.sdk.api.v3.assets.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.assets.model.AssetState
import org.tokend.sdk.api.v3.base.PageQueryParams
import org.tokend.sdk.utils.extentions.bitmask

/**
 * @see AssetParams.Includes
 */
open class AssetsPageParams(
        val policies: Collection<Long>? = null,
        val owner: String? = null,
        val state: AssetState? = null,
        include: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, include) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            policies?.also {
                putFilter("policy", policies.bitmask())
            }
            owner?.also { putFilter("owner", it) }
            state?.also { putFilter("state", it.value) }
        }
    }

    class Builder : PageQueryParams.Builder() {
        private var policies: Collection<Long>? = null
        private var owner: String? = null
        private var state: AssetState? = null

        fun withPolicies(policies: Collection<Long>) = also { this.policies = policies }

        fun withPolicies(vararg policies: Long) = also { this.policies = policies.asList() }

        fun withOwner(owner: String) = also { this.owner = owner }

        fun withState(state: AssetState) = also { this.state = state }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also {
            super.withPagingParams(pagingParams)
        }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): AssetsPageParams {
            return AssetsPageParams(policies, owner, state, include, pagingParams)
        }
    }
}