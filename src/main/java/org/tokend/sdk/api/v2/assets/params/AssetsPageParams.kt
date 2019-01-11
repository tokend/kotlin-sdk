package org.tokend.sdk.api.v2.assets.params

import org.tokend.sdk.api.base.params.PagingParamsHolder
import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v2.base.JsonApiQueryParams
import org.tokend.sdk.utils.extentions.bitmask
import org.tokend.wallet.xdr.AssetPolicy

/**
 * @see AssetParams.Includes
 */
open class AssetsPageParams(
        val policies: Collection<AssetPolicy>? = null,
        include: Collection<String>? = null,
        val pagingParams: PagingParamsV2? = null
) : AssetParams(include), PagingParamsHolder {
    override val order = pagingParams?.order
    override val cursor = pagingParams?.cursor
    override val limit = pagingParams?.limit

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            policies?.also {
                put("policy", policies.map { it.value.toLong() }.bitmask())
            }
            pagingParams?.also { putAll(it.map()) }
        }
    }

    class Builder : JsonApiQueryParams.Builder() {
        private var policies: Collection<AssetPolicy>? = null
        private var pagingParams: PagingParamsV2? = null

        fun withPolicies(policies: Collection<AssetPolicy>) = also { this.policies = policies }

        fun withPolicies(vararg policies: AssetPolicy) = also { this.policies = policies.toList() }

        fun withPagingParams(pagingParams: PagingParamsV2) = also { this.pagingParams = pagingParams }

        override fun build(): JsonApiQueryParams {
            return AssetsPageParams(policies, include, pagingParams)
        }
    }
}