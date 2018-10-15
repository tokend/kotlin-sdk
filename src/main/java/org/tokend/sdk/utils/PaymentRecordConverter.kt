package org.tokend.sdk.utils

import org.tokend.sdk.api.base.model.transactions.BaseTransaction
import org.tokend.sdk.api.base.model.transactions.InvestmentTransaction
import org.tokend.sdk.api.base.model.transactions.IssuanceTransaction
import org.tokend.sdk.api.base.model.transactions.MatchTransaction
import org.tokend.sdk.api.base.model.transactions.PaymentTransaction
import org.tokend.sdk.api.base.model.transactions.Transaction
import org.tokend.sdk.api.base.model.transactions.TransactionType
import org.tokend.sdk.api.base.model.transactions.WithdrawalTransaction
import org.tokend.sdk.api.accounts.model.PaymentRecord

object PaymentRecordConverter {
    fun toTransactions(items: List<PaymentRecord>,
                       contextAccountId: String, contextAsset: String): List<Transaction> {
        return items.map {
            when (TransactionType.fromLiteral(it.type)) {
                TransactionType.PAYMENT ->
                    listOf(PaymentTransaction.fromPaymentRecord(it, contextAccountId))
                TransactionType.ISSUANCE ->
                    listOf(IssuanceTransaction.fromPaymentRecord(it, contextAccountId))
                TransactionType.WITHDRAWAL ->
                    listOf(WithdrawalTransaction.fromPaymentRecord(it, contextAccountId))
                TransactionType.OFFER_MATCH ->
                    MatchTransaction.fromPaymentRecord(it, contextAsset, contextAccountId)
                TransactionType.INVESTMENT ->
                    InvestmentTransaction.fromPaymentRecord(it, contextAsset, contextAccountId)
                else ->
                    listOf(BaseTransaction.fromPaymentRecord(it, contextAccountId))
            }
        }.flatten()
    }
}