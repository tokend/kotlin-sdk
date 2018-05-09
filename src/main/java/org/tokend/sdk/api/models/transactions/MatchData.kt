package org.tokend.sdk.api.models.transactions

import java.math.BigDecimal

class MatchData(
        val quoteAsset: String,
        val quoteAmount: BigDecimal,
        val price: BigDecimal,
        val isBuy: Boolean,
        val orderId: String?
)