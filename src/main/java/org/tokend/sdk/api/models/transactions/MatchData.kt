package org.tokend.sdk.api.models.transactions

import java.math.BigDecimal

open class MatchData(
        val quoteAsset: String,
        val quoteAmount: BigDecimal,
        val price: BigDecimal,
        val isBuy: Boolean,
        val orderId: String?
)