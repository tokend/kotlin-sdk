package org.tokend.sdk.api.base.model.operations

import org.tokend.sdk.api.accounts.model.UnifiedOperationRecord
import org.tokend.sdk.api.trades.model.Offer
import java.math.BigDecimal
import java.math.MathContext

/**
 * Represents trading match.
 * It is related to the asset in whose history it is listed.
 * For example, if there is a match of buying 10 BTC for 100 ETH
 * then in BTC history it will be listed as "Bought 10 BTC for 100 ETH"
 * and in ETH history it will be listed as "Sold 100 ETH for 10 BTC"
 */
open class OfferMatchOperation(
        protected open val base: BaseTransferOperation,
        override val id: String,
        override val amount: BigDecimal,
        override val asset: String,
        val fee: BigDecimal,
        val feeAsset: String,
        val matchData: MatchData
) : TransferOperation by base {
    override val type = OperationType.OFFER_MATCH

    override val isReceived: Boolean
        get() = matchData.isBuy

    override val isSent: Boolean
        get() = !isReceived

    companion object {
        fun fromUnifiedOperationRecord(record: UnifiedOperationRecord, contextAsset: String,
                                       contextAccountId: String): List<OfferMatchOperation> {
            val ourParticipant = record.participants?.first {
                it.accountId == contextAccountId
            }

            val effect = ourParticipant?.effects?.firstOrNull()
                    ?: return emptyList()

            val effectBaseAsset = effect.baseAsset
                    ?: return emptyList()
            val effectQuoteAsset = effect.quoteAsset
                    ?: return emptyList()
            val contextAssetIsQuote = effectQuoteAsset == contextAsset

            return effect.matches
                    ?.asSequence()
                    ?.filter {
                        it.baseAmount.signum() != 0
                                && it.quoteAmount.signum() != 0
                    }
                    ?.mapIndexed { i, match ->
                        val baseAmount = match.baseAmount
                        val quoteAmount = match.quoteAmount

                        OfferMatchOperation(
                                base = BaseTransferOperation.fromPaymentRecord(record, contextAccountId),
                                id = "${record.id}_$i",
                                fee = match.fee,
                                asset = contextAsset,
                                amount = if (contextAssetIsQuote) quoteAmount else baseAmount,
                                matchData = MatchData(
                                        quoteAsset =
                                        if (contextAssetIsQuote) effectBaseAsset else effectQuoteAsset,
                                        quoteAmount =
                                        if (contextAssetIsQuote) baseAmount else quoteAmount,
                                        price =
                                        if (contextAssetIsQuote)
                                            BigDecimal.ONE.divide(match.price, MathContext.DECIMAL128)
                                        else
                                            match.price,
                                        isBuy = contextAssetIsQuote != effect.isBuy,
                                        orderId = null
                                ),
                                feeAsset = effectQuoteAsset
                        )
                    }
                    ?.toList() ?: emptyList()
        }

        fun fromOffer(offer: Offer): OfferMatchOperation {
            return OfferMatchOperation(
                    BaseTransferOperation(
                            pagingToken = offer.pagingToken,
                            date = offer.date,
                            state = OperationState.PENDING,
                            type = OperationType.OFFER_MATCH,
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