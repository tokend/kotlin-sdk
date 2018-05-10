package org.tokend.sdk.api.models.transactions

import org.tokend.sdk.api.models.PaymentRecord
import org.tokend.sdk.utils.BigDecimalUtil
import java.math.BigDecimal

open class MatchTransaction(
        protected open val base: BaseTransaction,
        override val id: String,
        override val amount: BigDecimal,
        override val asset: String,
        val fee: BigDecimal,
        val matchData: MatchData
) : Transaction by base {
    override val type = TransactionType.OFFER_MATCH

    override fun isSent(account: String): Boolean {
        return !matchData.isBuy
    }

    override fun isReceived(account: String): Boolean {
        return !isSent(account)
    }

    companion object {
        fun fromPaymentRecord(record: PaymentRecord, contextAsset: String,
                              contextAccountId: String): List<MatchTransaction> {
            val ourParticipant = record.participants?.first {
                it.accountId == contextAccountId
            }

            val effect = ourParticipant?.effects?.firstOrNull()

            val baseAsset = effect?.baseAsset ?: ""
            val quoteAsset = effect?.quoteAsset ?: ""
            val contextAssetIsQuote = quoteAsset == contextAsset

            return effect?.matches?.mapIndexed { i, match ->
                val baseAmount = match.baseAmount
                val quoteAmount = match.quoteAmount

                MatchTransaction(
                        base = BaseTransaction.fromPaymentRecord(record),
                        id = "${record.id}_i",
                        fee = BigDecimalUtil.valueOf(match.feeString),
                        asset = if (contextAssetIsQuote) quoteAsset else baseAsset,
                        amount = if (contextAssetIsQuote) quoteAmount else baseAmount,
                        matchData = MatchData(
                                quoteAsset =
                                if (contextAssetIsQuote) baseAsset else quoteAsset,
                                quoteAmount =
                                if (contextAssetIsQuote) baseAmount else quoteAmount,
                                price = match.price,
                                isBuy = !contextAssetIsQuote && effect.isBuy
                                        || contextAssetIsQuote && !effect.isBuy,
                                orderId = null
                        )
                )
            } ?: listOf()
        }
    }
}