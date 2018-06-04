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
    val isReceived: Boolean

    val isSent: Boolean
        get() = !isReceived
}