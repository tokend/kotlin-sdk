package org.tokend.sdk.api.identity.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams

open class IdentitiesPageParams(
        val email: String? = null,
        val address: String? = null,
        include: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            email?.also { putFilter("email", it) }
            address?.also { putFilter("address", it) }
        }
    }

    class Builder : PageQueryParams.Builder() {
        private var email: String? = null
        private var address: String? = null

        fun withEmail(email: String) = also { this.email = email }

        fun withAddress(address: String) = also { this.address = address }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also {
            super.withPagingParams(pagingParams)
        }

        override fun build(): IdentitiesPageParams {
            return IdentitiesPageParams(email, address, include, pagingParams)
        }
    }
}