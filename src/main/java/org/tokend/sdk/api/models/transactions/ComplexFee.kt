package org.tokend.sdk.api.models.transactions

import java.math.BigDecimal

class ComplexFee(
        val fixed: BigDecimal,
        val percent: BigDecimal
)