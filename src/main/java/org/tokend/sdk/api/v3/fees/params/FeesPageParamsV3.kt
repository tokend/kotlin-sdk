package org.tokend.sdk.api.v3.fees.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams
import org.tokend.sdk.utils.BigDecimalUtil
import org.tokend.wallet.xdr.FeeType
import java.math.BigDecimal

/**
 * @see FeesPageParamsV3.Includes
 */
open class FeesPageParamsV3(
        val asset: String? = null,
        val type: FeeType? = null,
        val subtype: Int? = null,
        val account: String? = null,
        val accountRole: Long? = null,
        val lowerBound: BigDecimal? = null,
        val upperBound: BigDecimal? = null,
        include: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, include) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            asset?.also { putFilter("asset", it) }
            type?.also { putFilter("fee_type", it.value) }
            subtype?.also { putFilter("subtype", it) }
            account?.also { putFilter("account", it) }
            accountRole?.also { putFilter("account_role", it) }
            lowerBound?.also { putFilter("lower_bound", BigDecimalUtil.toPlainString(it)) }
            upperBound?.also { putFilter("upper_bound", BigDecimalUtil.toPlainString(it)) }
        }
    }

    class Builder : PageQueryParams.Builder() {
        private var asset: String? = null
        private var type: FeeType? = null
        private var subtype: Int? = null
        private var account: String? = null
        private var accountRole: Long? = null
        private var lowerBound: BigDecimal? = null
        private var upperBound: BigDecimal? = null

        fun withAsset(asset: String) = also { this.asset = asset }

        fun withType(type: FeeType) = also { this.type = type }

        fun withSubtype(subtype: Int) = also { this.subtype = subtype }

        fun withAccount(account: String) = also { this.account = account }

        fun withAccountRole(accountRole: Long) = also { this.accountRole = accountRole }

        fun withLowerBound(lowerBound: BigDecimal) = also { this.lowerBound = lowerBound }

        fun withUpperBound(upperBound: BigDecimal) = also { this.upperBound = upperBound }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also {
            super.withPagingParams(pagingParams)
        }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): FeesPageParamsV3 {
            return FeesPageParamsV3(asset, type, subtype, account, accountRole, lowerBound, upperBound, include, pagingParams)
        }
    }

    class Includes {
        companion object {
            const val ACCOUNT = "account"
            const val ACCOUNT_ROLE = "account_role"
            const val ASSET = "asset"
        }
    }
}