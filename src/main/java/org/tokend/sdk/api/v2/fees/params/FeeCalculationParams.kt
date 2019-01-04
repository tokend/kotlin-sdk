package org.tokend.sdk.api.v2.fees.params

import org.tokend.sdk.utils.BigDecimalUtil
import org.tokend.wallet.xdr.FeeType
import java.math.BigDecimal

/**
 * @see FeeParamsV2.Includes
 */
class FeeCalculationParams(
        val type: FeeType,
        val asset: String,
        val account: String? = null,
        val amount: BigDecimal? = null,
        val subtype: Int? = null,
        include: Collection<String>? = null
) : FeeParamsV2(include) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            put("fee_type", type.value)
            put("asset", asset)
            account?.also { put("account_id", it) }
            amount?.also { put("amount", BigDecimalUtil.toPlainString(amount)) }
            subtype?.also { put("subtype", it) }
        }
    }
}