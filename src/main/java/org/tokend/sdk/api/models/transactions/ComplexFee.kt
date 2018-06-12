package org.tokend.sdk.api.models.transactions

import java.math.BigDecimal

open class ComplexFee(
        val fixed: BigDecimal,
        val percent: BigDecimal
) {
    val total: BigDecimal
        get() = fixed + percent
}