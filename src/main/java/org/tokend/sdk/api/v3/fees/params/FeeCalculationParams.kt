package org.tokend.sdk.api.v3.fees.params

import org.tokend.sdk.utils.BigDecimalUtil
import java.math.BigDecimal

class FeeCalculationParams(
        val asset: String,
        val type: Int,
        val subtype: Int,
        val amount: BigDecimal
) {
    fun map(): Map<String, Any> {
        return mutableMapOf<String, Any>().apply {
            put("asset", asset)
            put("fee_type", type)
            put("subtype", subtype)
            put("amount", BigDecimalUtil.toPlainString(amount))
        }
    }
}