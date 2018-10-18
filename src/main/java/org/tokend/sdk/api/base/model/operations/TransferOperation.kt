package org.tokend.sdk.api.base.model.operations

import org.tokend.sdk.api.accounts.model.UnifiedOperationRecord
import java.math.BigDecimal
import java.util.*

/**
 * Represents an operation of transferring some amount of some asset.
 * It's a synthetic entity parsed from [UnifiedOperationRecord],
 * clear to understand and ready to display.
 */
interface TransferOperation {
    val id: String
    val pagingToken: String
    val date: Date
    val state: OperationState
    val type: OperationType
    val sourceAccount: String
    val amount: BigDecimal
    val asset: String
    val isReceived: Boolean

    val isSent: Boolean
        get() = !isReceived
}