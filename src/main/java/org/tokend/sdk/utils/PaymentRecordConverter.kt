package org.tokend.sdk.utils

import org.tokend.sdk.api.models.PaymentRecord
import org.tokend.sdk.api.models.transactions.*

object PaymentRecordConverter {
    fun toTransactions(items: List<PaymentRecord>,
                       contextAccountId: String, contextAsset: String): List<Transaction> {
        return items.map {
            when (it.type) {
                EmissionTransaction.TYPE_ISSUANCE ->
                    listOf(EmissionTransaction.fromRecord(it))
                WithdrawTransaction.TYPE_WITHDRAW ->
                    listOf(WithdrawTransaction.fromRecord(it))
                InvestmentTransaction.TYPE_INVESTMENT ->
                    InvestmentTransaction.fromRecord(it, contextAsset, contextAccountId)
                OfferMatchTransaction.TYPE_MANAGE_OFFER ->
                        OfferMatchTransaction.fromRecord(it, contextAsset, contextAccountId)
                else -> listOf(Transaction.fromRecord(it))
            }
        }.fold(mutableListOf<Transaction>(), { acc, transactions ->
            acc.addAll(transactions)
            acc
        }).toList()
    }
}