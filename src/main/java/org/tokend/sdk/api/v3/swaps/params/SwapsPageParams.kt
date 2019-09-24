package org.tokend.sdk.api.v3.swaps.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams
import org.tokend.sdk.api.v3.swaps.model.SwapState

/**
 * @see SwapParams.Includes
 */
class SwapsPageParams(
        val source: String? = null,
        val destination: String? = null,
        val sourceBalance: String? = null,
        val destinationBalance: String? = null,
        val state: SwapState? = null,
        pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            state?.also { putFilter("state", it.value) }
            source?.also { putFilter("source", it) }
            destination?.also { putFilter("destination", it) }
            sourceBalance?.also { putFilter("source_balance", it) }
            destinationBalance?.also { putFilter("destination_balance", it) }
        }
    }

    open class Builder : PageQueryParams.Builder() {
        protected var source: String? = null
        protected var destination: String? = null
        protected var sourceBalance: String? = null
        protected var destinationBalance: String? = null
        protected var state: SwapState? = null

        open fun withSource(source: String) = also { this.source = source }

        open fun withDestination(destination: String) = also { this.destination = destination }

        open fun withSourceBalance(sourceBalance: String) = also { this.sourceBalance = sourceBalance }

        open fun withDestinationBalance(destinationBalance: String) = also { this.destinationBalance = destinationBalance }

        open fun withState(state: SwapState) = also { this.state = state }

        override fun withInclude(include: Collection<String>?) = also { super.withInclude(include) }

        override fun withInclude(vararg include: String) = also { super.withInclude(*include) }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also { super.withPagingParams(pagingParams) }

        override fun build(): SwapsPageParams {
            return SwapsPageParams(source, destination, sourceBalance, destinationBalance, state, pagingParams, include)
        }
    }
}