package org.tokend.sdk.api.base.model.operations

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.accounts.model.UnifiedOperationRecord
import org.tokend.sdk.api.trades.model.Offer
import java.math.BigDecimal
import java.math.MathContext

/**
 * Represents investment in some fund.
 * It is related to the asset in whose history it is listed.
 * For example, if there is an investment of 10 BTC to RTOKEN fund that
 * gave 100 RTOKEN then in BTC history it will be listed as "Spent 10 BTC investing in RTOKEN"
 * and in ETH history it will be listed as "Got 100 RTOKEN from BTC investment"
 */
open class InvestmentOperation(
        base: BaseTransferOperation,
        id: String,
        amount: BigDecimal,
        asset: String,
        fee: BigDecimal,
        feeAsset: String,
        matchData: MatchData
) : OfferMatchOperation(base, id, amount, asset, fee, feeAsset, matchData) {
    @SerializedName("investment_type")
    override val type = OperationType.INVESTMENT

    companion object {
        fun fromUnifiedOperationRecord(record: UnifiedOperationRecord, contextAsset: String,
                                       contextAccountId: String): List<InvestmentOperation> {
            val ourParticipants = record.participants?.filter {
                it.accountId == contextAccountId
            }

            val contextAssetIsBase = ourParticipants?.find {
                it.effects?.find { it.baseAsset == contextAsset } != null
            } != null

            // If we have investments in some token and we are looking
            // for transactions for this token, we would like to get all investments.
            // Otherwise we would like to get only investment made with this token.
            val ourParticipant =
                    if (contextAssetIsBase)
                        ourParticipants?.maxBy { it.effects?.size ?: 0 }
                    else
                        ourParticipants?.find {
                            it.effects?.size == 1
                                    && it.effects.find { it.quoteAsset == contextAsset } != null
                        }

            ourParticipant ?: return emptyList()

            return ourParticipant.effects
                    ?.asSequence()
                    ?.filter {
                        it.baseAsset != null
                                && it.quoteAsset != null
                    }
                    ?.mapIndexed { i, effect ->
                        val matches = effect.matches

                        val baseAsset = effect.baseAsset!!
                        val quoteAsset = effect.quoteAsset!!

                        val quoteAmount = matches?.fold(BigDecimal.ZERO) { acc, item ->
                            acc.add(item.quoteAmount)
                        } ?: BigDecimal.ZERO
                        val baseAmount = matches?.fold(BigDecimal.ZERO) { acc, item ->
                            acc.add(item.baseAmount)
                        } ?: BigDecimal.ZERO
                        val fee = matches?.fold(BigDecimal.ZERO) { acc, item ->
                            acc.add(item.fee)
                        } ?: BigDecimal.ZERO

                        val price =
                                (if (baseAmount == null || baseAmount.signum() == 0)
                                    null
                                else quoteAmount?.divide(baseAmount, MathContext.DECIMAL128))
                                        ?: BigDecimal.ONE

                        val contextAssetIsQuote = !contextAssetIsBase

                        InvestmentOperation(
                                base = BaseTransferOperation.fromPaymentRecord(record, contextAccountId),
                                id = "${record.id}_$i",
                                fee = fee,
                                asset = contextAsset,
                                amount = if (contextAssetIsQuote) quoteAmount else baseAmount,
                                matchData = MatchData(
                                        quoteAsset =
                                        if (contextAssetIsQuote) baseAsset else quoteAsset,
                                        quoteAmount =
                                        if (contextAssetIsQuote) baseAmount else quoteAmount,
                                        price =
                                        if (contextAssetIsQuote)
                                            BigDecimal.ONE.divide(price, MathContext.DECIMAL128)
                                        else
                                            price,
                                        isBuy = contextAssetIsQuote != effect.isBuy,
                                        orderId = null
                                ),
                                feeAsset = quoteAsset
                        )
                    }
                    ?.toList() ?: listOf()
        }

        fun fromOffer(offer: Offer): InvestmentOperation {
            return InvestmentOperation(
                    BaseTransferOperation(
                            pagingToken = offer.pagingToken,
                            date = offer.date,
                            state = OperationState.PENDING,
                            type = OperationType.INVESTMENT,
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
                            orderId = offer.id,
                            orderBookId = offer.orderBookId
                    ),
                    feeAsset = offer.quoteAsset
            )
        }
    }
}