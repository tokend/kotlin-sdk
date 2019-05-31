package org.tokend.sdk.api.v3.balances.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

/**
 * @see ConvertedBalancesParams.Includes
 */
class ConvertedBalancesParams(
        include: Collection<String>?
) : JsonApiQueryParams(include) {

    class Builder: JsonApiQueryParams.Builder() {
        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }
    }

    class Includes {
        companion object {
            const val ASSET = "asset"
            const val STATES = "states"
            const val BALANCE = "balance"
            const val BALANCE_ASSET = "balance.asset"
            const val BALANCE_STATE = "balance.state"
        }
    }
}