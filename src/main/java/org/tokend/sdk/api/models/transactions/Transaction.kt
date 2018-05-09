package org.tokend.sdk.api.models.transactions

import java.math.BigDecimal
import java.util.*

interface Transaction {
    val id: String
    val pagingToken: String
    val date: Date
    val state: TransactionState
    val type: TransactionType
    val sourceAccount: String
    val amount: BigDecimal
    val asset: String

    /**
     * @return [true] if given account is the sender of this transaction
     */
    fun isSent(account: String): Boolean

    /**
     * @return [true] if given account is the recipient of this transaction
     */
    fun isReceived(account: String): Boolean {
        return !isSent(account)
    }
}