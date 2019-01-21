package org.tokend.sdk.api.v2.assets.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v2.base.PageQueryParams
import org.tokend.sdk.utils.extentions.bitmask
import org.tokend.wallet.xdr.AssetPolicy

/**
 * @see AssetParams.Includes
 */
open class AssetsPageParams(
        val policies: Collection<AssetPolicy>? = null,
        val owner: String? = null,
        include: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, include) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            policies?.also {
                put("filter[policy]", policies.map { it.value.toLong() }.bitmask())
            }
            owner?.also { put("filter[owner]", it) }
        }
    }

    class Builder : PageQueryParams.Builder() {
        private var policies: Collection<AssetPolicy>? = null
        private var owner: String? = null

        fun withPolicies(policies: Collection<AssetPolicy>) = also { this.policies = policies }

        fun withPolicies(vararg policies: AssetPolicy) = also { this.policies = policies.toList() }

        fun withOwner(owner: String) = also { this.owner = owner }

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
            return AssetsPageParams(policies, owner, include, pagingParams)
        }
    }
}