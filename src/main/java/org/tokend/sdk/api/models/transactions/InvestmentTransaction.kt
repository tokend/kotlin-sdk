package org.tokend.sdk.api.models.transactions

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.models.PaymentRecord
import java.math.BigDecimal
import java.math.MathContext

class InvestmentTransaction(
        base: BaseTransaction,
        id: String,
        amount: BigDecimal,
        asset: String,
        fee: BigDecimal,
        feeAsset: String,
        matchData: MatchData
) : MatchTransaction(base, id, amount, asset, fee, feeAsset, matchData) {
    @SerializedName("investment_type")
    override val type = TransactionType.INVESTMENT

    companion object {
        fun fromPaymentRecord(record: PaymentRecord, contextAsset: String,
                              contextAccountId: String): List<InvestmentTransaction> {
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

            return ourParticipant?.effects?.mapIndexed { i, effect ->
                val matches = effect.matches

                val baseAsset = effect.baseAsset ?: ""
                val quoteAsset = effect.quoteAsset ?: ""

                val quoteAmount = matches?.fold(BigDecimal.ZERO, { acc, item ->
                    acc.add(item.quoteAmount)
                }) ?: BigDecimal.ZERO
                val baseAmount = matches?.fold(BigDecimal.ZERO, { acc, item ->
                    acc.add(item.baseAmount)
                }) ?: BigDecimal.ZERO
                val fee = matches?.fold(BigDecimal.ZERO, { acc, item ->
                    acc.add(item.fee)
                }) ?: BigDecimal.ZERO

                val price =
                        (if (baseAmount == null || baseAmount.signum() == 0)
                            null
                        else quoteAmount?.divide(baseAmount, MathContext.DECIMAL128))
                                ?: BigDecimal.ONE

                val contextAssetIsQuote = !contextAssetIsBase

                InvestmentTransaction(
                        base = BaseTransaction.fromPaymentRecord(record, contextAccountId),
                        id = "${record.id}_$i",
                        fee = fee,
                        asset = if (contextAssetIsQuote) quoteAsset else baseAsset,
                        amount = if (contextAssetIsQuote) quoteAmount else baseAmount,
                        matchData = MatchData(
                                quoteAsset =
                                if (contextAssetIsQuote) baseAsset else quoteAsset,
                                quoteAmount =
                                if (contextAssetIsQuote) baseAmount else quoteAmount,
                                price = price,
                                isBuy = !contextAssetIsQuote && effect.isBuy,
                                orderId = null
                        ),
                        feeAsset = quoteAsset
                )
            } ?: listOf()
        }
    }
}