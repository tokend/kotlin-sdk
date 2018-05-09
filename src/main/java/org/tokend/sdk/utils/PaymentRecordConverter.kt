package org.tokend.sdk.utils

import org.tokend.sdk.api.models.PaymentRecord
import org.tokend.sdk.api.models.transactions.*

object PaymentRecordConverter {
    fun toTransactions(items: List<PaymentRecord>,
                       contextAccountId: String, contextAsset: String): List<Transaction> {
        return items.map {
            when (TransactionType.fromLiteral(it.type)) {
                TransactionType.PAYMENT ->
                    listOf(PaymentTransaction.fromPaymentRecord(it))
                TransactionType.ISSUANCE ->
                    listOf(IssuanceTransaction.fromPaymentRecord(it))
                TransactionType.WITHDRAWAL ->
                    listOf(WithdrawalTransaction.fromPaymentRecord(it))
                TransactionType.OFFER_MATCH ->
                    MatchTransaction.fromPaymentRecord(it, contextAsset, contextAccountId)
                TransactionType.INVESTMENT ->
                    InvestmentTransaction.fromPaymentRecord(it, contextAsset, contextAccountId)
                else ->
                    listOf(BaseTransaction.fromPaymentRecord(it))
            }
        }.fold(mutableListOf<Transaction>(), { acc, transactions ->
            acc.addAll(transactions)
            acc
        }).toList()
    }
}