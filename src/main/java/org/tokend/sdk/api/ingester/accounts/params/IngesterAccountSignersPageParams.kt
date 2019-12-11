package org.tokend.sdk.api.ingester.accounts.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.ingester.accounts.params.IngesterAccountSignersPageParams.Includes
import org.tokend.sdk.api.v3.base.PageQueryParams

/**
 * @see Includes
 */
open class IngesterAccountSignersPageParams(
        pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : PageQueryParams(pagingParams, include) {

    open class Builder: PageQueryParams.Builder() {
        override fun withInclude(include: Collection<String>?) = also { super.withInclude(include) }

        override fun withInclude(vararg include: String) = also { super.withInclude(*include) }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also { super.withPagingParams(pagingParams) }

        override fun build(): IngesterAccountSignersPageParams =
                IngesterAccountSignersPageParams(pagingParams, include)
    }

    class Includes {
        companion object {
            const val ROLE = "role"
            const val ROLE_RULES = "role.rules"
        }
    }
}