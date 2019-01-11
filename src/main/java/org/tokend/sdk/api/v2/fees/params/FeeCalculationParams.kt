package org.tokend.sdk.api.v2.fees.params

import org.tokend.sdk.api.v2.base.JsonApiQueryParams
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

    class Builder(private val type: FeeType, private val asset: String) : JsonApiQueryParams.Builder() {
        private var account: String? = null
        private var amount: BigDecimal? = null
        private var subtype: Int? = null

        fun withAccount(account: String) = also { this.account = account }

        fun withAmount(amount: BigDecimal) = also { this.amount = amount }

        fun withSubtype(subtype: Int) = also { this.subtype = subtype }

        override fun build(): FeeCalculationParams {
            return FeeCalculationParams(type, asset, account, amount, subtype, include)
        }
    }
}