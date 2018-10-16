package org.tokend.sdk.api.base.model.transactions

import java.math.BigDecimal
import java.util.*

/**
 * Represents an operation of transferring some amount of some asset.
 */
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