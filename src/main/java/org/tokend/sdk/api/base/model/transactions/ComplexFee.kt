package org.tokend.sdk.api.base.model.transactions

import java.math.BigDecimal

open class ComplexFee(
        val fixed: BigDecimal,
        val percent: BigDecimal,
        val asset: String
) {
    val total: BigDecimal
        get() = fixed + percent
}