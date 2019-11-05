package org.tokend.sdk.api.v3.history.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams
import org.tokend.wallet.xdr.OperationType

/**
 * @see OperationParams.Includes
 */
open class OperationsPageParams(
        val types: Collection<OperationType>? = null,
        pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            types?.also { types ->
                putFilter("types", types.joinToString(",", transform = { it.value.toString() }))
            }
        }
    }

    open class Builder : PageQueryParams.Builder() {
        protected open var types: kotlin.collections.Collection<org.tokend.wallet.xdr.OperationType>? = null

        open fun withTypes(types: kotlin.collections.Collection<org.tokend.wallet.xdr.OperationType>) = also { this.types = types }

        open fun withTypes(vararg types: OperationType) = withTypes(types.toList())

        override fun withInclude(include: Collection<String>?) = also { super.withInclude(include) }

        override fun withInclude(vararg include: String) = also { super.withInclude(*include) }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also { super.withPagingParams(pagingParams) }

        override fun build(): OperationsPageParams =
                OperationsPageParams(types, pagingParams, include)
    }
}