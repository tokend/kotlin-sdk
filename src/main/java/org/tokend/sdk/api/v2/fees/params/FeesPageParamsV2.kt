package org.tokend.sdk.api.v2.fees.params

import org.tokend.sdk.api.base.params.PagingParamsHolder
import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.utils.BigDecimalUtil
import org.tokend.wallet.xdr.AccountType
import org.tokend.wallet.xdr.FeeType
import java.math.BigDecimal

/**
 * @see FeeParamsV2.Includes
 */
open class FeesPageParamsV2(
        val asset: String? = null,
        val type: FeeType? = null,
        val subtype: Int? = null,
        val account: String? = null,
        val accountType: AccountType? = null,
        val lowerBound: BigDecimal? = null,
        val upperBound: BigDecimal? = null,
        include: Collection<String>? = null,
        val pagingParams: PagingParamsV2? = null
) : FeeParamsV2(include), PagingParamsHolder {
    override val order = pagingParams?.order
    override val cursor = pagingParams?.cursor
    override val limit = pagingParams?.limit

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            asset?.also { put("asset", it) }
            type?.also { put("fee_type", it.value) }
            subtype?.also { put("subtype", it) }
            account?.also { put("account_id", it) }
            accountType?.also { put("account_type", it.value) }
            lowerBound?.also { put("lower_bound", BigDecimalUtil.toPlainString(it)) }
            upperBound?.also { put("upper_bound", BigDecimalUtil.toPlainString(it)) }
            pagingParams?.also { putAll(it.map()) }
        }
    }
}