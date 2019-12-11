package org.tokend.sdk.api.ingester.history.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.ingester.history.params.IngesterOperationsPageParams.Includes
import org.tokend.sdk.api.v3.base.PageQueryParams

/**
 * @see Includes
 */
open class IngesterOperationsPageParams(
        open val types: Collection<Int>? = null,
        pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> = super.map().toMutableMap().apply {
        types?.also { putFilter("types", it.joinToString(",")) }
    }

    open class Builder : PageQueryParams.Builder() {
        protected open var types: kotlin.collections.Collection<kotlin.Int>? = null

        open fun withTypes(types: kotlin.collections.Collection<kotlin.Int>) = also { this.types = types }

        open fun withTypes(vararg types: kotlin.Int) = also { this.types = types.asList() }

        override fun withInclude(include: Collection<String>?) = also { super.withInclude(include) }

        override fun withInclude(vararg include: String) = also { super.withInclude(*include) }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also { super.withPagingParams(pagingParams) }

        override fun build(): IngesterOperationsPageParams =
                IngesterOperationsPageParams(types, pagingParams, include)
    }

    companion object Includes {
        const val OPERATION_DETAILS = "operation.details"
    }
}