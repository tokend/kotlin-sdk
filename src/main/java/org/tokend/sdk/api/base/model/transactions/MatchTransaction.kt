package org.tokend.sdk.api.base.model.transactions

import org.tokend.sdk.api.trades.model.Offer
import org.tokend.sdk.api.accounts.model.PaymentRecord
import org.tokend.sdk.utils.BigDecimalUtil
import java.math.BigDecimal

/**
 * Represents trading match.
 * It is related to the asset in whose history it is listed.
 * For example, if there is a match of buying 10 BTC for 100 ETH
 * then in BTC history it will be listed as "Bought 10 BTC for 100 ETH"
 * and in ETH history it will be listed as "Sold 100 ETH for 10 BTC"
 */
open class MatchTransaction(
        protected open val base: BaseTransaction,
        override val id: String,
        override val amount: BigDecimal,
        override val asset: String,
        val fee: BigDecimal,
        val feeAsset: String,
        val matchData: MatchData
) : Transaction by base {
    override val type = TransactionType.OFFER_MATCH

    override val isReceived: Boolean
        get() = matchData.isBuy

    override val isSent: Boolean
        get() = !isReceived

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
                        base = BaseTransaction.fromPaymentRecord(record, contextAccountId),
                        id = "${record.id}_$i",
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
                        ),
                        feeAsset = quoteAsset
                )
            } ?: listOf()
        }

        fun fromOffer(offer: Offer): MatchTransaction {
            return MatchTransaction(
                    BaseTransaction(
                            pagingToken = offer.pagingToken,
                            date = offer.date,
                            state = TransactionState.PENDING,
                            type = TransactionType.OFFER_MATCH,
                            sourceAccount = "",
                            asset = "",
                            amount = BigDecimal.ZERO,
                            id = "",
                            isReceived = false
                    ),
                    id = offer.id.toString(),
                    fee = offer.fee ?: BigDecimal.ZERO,
                    amount = offer.baseAmount,
                    asset = offer.baseAsset,
                    matchData = MatchData(
                            quoteAmount = offer.quoteAmount,
                            quoteAsset = offer.quoteAsset,
                            price = offer.price,
                            isBuy = offer.isBuy,
                            orderId = offer.id
                    ),
                    feeAsset = offer.quoteAsset
            )
        }
    }
}