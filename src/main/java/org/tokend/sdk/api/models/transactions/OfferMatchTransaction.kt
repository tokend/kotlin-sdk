package org.tokend.sdk.api.models.transactions

import org.tokend.sdk.api.models.PaymentRecord

class OfferMatchTransaction(
        amount: String?,
        asset: String?,
        state: PaymentState,
        id: String?,
        pagingToken: String?,
        createdAt: String?,
        val isIncome: Boolean,
        val quoteAmount: String?,
        val quoteAsset: String?,
        val price: String?,
        fee: String?,
        toBalance: String?,
        toAccount: String?) :
        Transaction(
                amount = amount,
                assetString = asset,
                createdAt = createdAt,
                type = TYPE_MANAGE_OFFER,
                state = state,
                id = id,
                pagingToken = pagingToken,
                toBalance = toBalance,
                to = toAccount,
                sourcePaymentFee = fee,
                fromBalance = null,
                participants = null
        ) {
    override fun isReceived(accountId: String) = isIncome

    companion object {
        val TYPE_MANAGE_OFFER = "manage_offer"

        fun fromRecord(record: PaymentRecord, contextAsset: String, contextAccountId: String)
                : List<OfferMatchTransaction> {
            val ourParticipant = record.participants?.first {
                it.accountId == contextAccountId
            }

            val effect = ourParticipant?.effects?.firstOrNull()

            val baseAsset = effect?.baseAsset
            val quoteAsset = effect?.quoteAsset
            val contextAssetIsQuote = quoteAsset == contextAsset

            return effect?.matches?.mapIndexed { i, match ->
                val baseAmount = match.baseAmountString
                val quoteAmount = match.quoteAmountString

                OfferMatchTransaction(
                        amount = if (contextAssetIsQuote) quoteAmount else baseAmount,
                        asset = if (contextAssetIsQuote) quoteAsset else baseAsset,
                        quoteAmount = if (contextAssetIsQuote) baseAmount else quoteAmount,
                        quoteAsset = if (contextAssetIsQuote) baseAsset else quoteAsset,
                        isIncome = !contextAssetIsQuote && effect.isBuy
                                || contextAssetIsQuote && !effect.isBuy,
                        fee = match.feeString,
                        id = "${record.id} $i",
                        pagingToken = record.pagingToken,
                        state = PaymentState.fromCode(record.state),
                        toBalance = ourParticipant.balanceId,
                        toAccount = ourParticipant.accountId,
                        createdAt = record.ledgerCloseTime,
                        price = match.priceString
                )
            } ?: listOf()
        }
    }
}