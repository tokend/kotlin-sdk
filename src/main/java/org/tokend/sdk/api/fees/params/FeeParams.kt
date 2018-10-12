package org.tokend.sdk.api.fees.params

import org.tokend.sdk.api.base.params.QueryParams
import org.tokend.sdk.utils.BigDecimalUtil
import java.math.BigDecimal

open class FeeParams(
        val asset: String,
        val accountId: String? = null,
        val amount: BigDecimal? = null,
        val subtype: Int? = null
) : QueryParams {
    override fun map(): Map<String, Any> {
        return mutableMapOf<String, Any>()
                .apply {
                    asset.also { put("asset", it) }
                    accountId?.also { put("account", it) }
                    amount?.also { put("amount", BigDecimalUtil.toPlainString(amount)) }
                    subtype?.also { put("subtype", it) }
                }
    }
}