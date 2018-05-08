package org.tokend.sdk.api.models.transactions

import org.tokend.sdk.api.models.Offer
import org.tokend.sdk.api.models.Participant
import org.tokend.sdk.api.models.PaymentRecord
import java.math.BigDecimal
import java.math.MathContext

/**
 * Created by Oleg Koretsky on 1/4/18.
 */
class InvestmentTransaction(amount: String?,
                            asset: String?,
                            val quoteAmount: String?,
                            val quoteAsset: String?,
                            val price: String?,
                            val isIncome: Boolean,
                            createdAt: String?,
                            paymentFee: String?,
                            type: String?,
                            state: PaymentState?,
                            id: String?,
                            pagingToken: String?,
                            participants: List<Participant>?,
                            toBalance: String?,
                            toAccount: String?) :
        Transaction(amount = amount,
                assetString = asset,
                createdAt = createdAt,
                type = type,
                state = state,
                id = id,
                pagingToken = pagingToken,
                fromBalance = null,
                toBalance = toBalance,
                to = toAccount,
                participants = participants,
                sourcePaymentFee = paymentFee) {


    override fun isReceived(accountId: String) = isIncome

    companion object {
        const val TYPE_INVESTMENT = "check_sale_state"

        fun fromRecord(record: PaymentRecord, contextAsset: String, contextAccountId: String)
                : List<InvestmentTransaction> {
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

                val baseAsset = effect.baseAsset
                val quoteAsset = effect.quoteAsset

                val quoteAmount = matches?.fold(BigDecimal.ZERO, { acc, item ->
                    acc.add(item.quoteAmount)
                })
                val baseAmount = matches?.fold(BigDecimal.ZERO, { acc, item ->
                    acc.add(item.baseAmount)
                })
                val fee = matches?.fold(BigDecimal.ZERO, { acc, item ->
                    acc.add(item.fee)
                })?.toPlainString()

                val price =
                        if (baseAmount == null || baseAmount.signum() == 0)
                            null
                        else quoteAmount?.divide(baseAmount, MathContext.DECIMAL128)

                val contextAssetIsQuote = !contextAssetIsBase

                InvestmentTransaction(
                        amount = if (contextAssetIsQuote) quoteAmount?.toPlainString() else baseAmount?.toPlainString(),
                        asset = if (contextAssetIsQuote) quoteAsset else baseAsset,
                        quoteAmount = if (contextAssetIsQuote) baseAmount?.toPlainString() else quoteAmount?.toPlainString(),
                        quoteAsset = if (contextAssetIsQuote) baseAsset else quoteAsset,
                        isIncome = !contextAssetIsQuote && (effect.isBuy ?: false),
                        price = price?.toPlainString(),
                        createdAt = record.ledgerCloseTime,
                        type = record.type,
                        state = PaymentState.fromCode(record.state),
                        id = "${record.id} $i",
                        pagingToken = record.pagingToken,
                        participants = record.participants,
                        paymentFee = fee,
                        toBalance = ourParticipant.balanceId,
                        toAccount = ourParticipant.accountId
                )
            } ?: listOf()
        }

        fun fromOffer(offer: Offer): InvestmentTransaction {
            return InvestmentTransaction(
                    amount = offer.quoteAmount,
                    asset = offer.quoteAsset,
                    quoteAsset = offer.baseAsset,
                    quoteAmount = offer.baseAmount,
                    price = offer.price,
                    isIncome = false,
                    createdAt = offer.createdAt,
                    pagingToken = offer.pagingToken,
                    state = PaymentState.SUCCESS,
                    id = offer.id,
                    type = TYPE_INVESTMENT,
                    toBalance = offer.baseBalance,
                    paymentFee = offer.fee,
                    toAccount = null,
                    participants = null
            )
        }
    }
}