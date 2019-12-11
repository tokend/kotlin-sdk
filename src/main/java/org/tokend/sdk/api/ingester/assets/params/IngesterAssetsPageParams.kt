package org.tokend.sdk.api.ingester.assets.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams

/**
 * @see IngesterAssetParams.Includes
 */
open class IngesterAssetsPageParams(
        open val owner: String? = null,
        open val state: String? = null,
        pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> =  super.map().toMutableMap().apply {
        owner?.also { putFilter("owner", it) }
        state?.also { putFilter("state", it) }
    }

    open class Builder: PageQueryParams.Builder() {
        protected open var owner: kotlin.String? = null
        protected open var state: kotlin.String? = null

        open fun withOwner(owner: kotlin.String) = also { this.owner = owner }

        open fun withState(state: kotlin.String) = also { this.state = state }

        override fun withInclude(include: Collection<String>?) = also { super.withInclude(include) }

        override fun withInclude(vararg include: String) = also { super.withInclude(*include) }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also { super.withPagingParams(pagingParams) }

        override fun build(): IngesterAssetsPageParams =
                IngesterAssetsPageParams(owner, state, pagingParams, include)
    }
}