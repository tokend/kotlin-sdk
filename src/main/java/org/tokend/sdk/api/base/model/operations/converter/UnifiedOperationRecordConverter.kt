package org.tokend.sdk.api.base.model.operations.converter

import org.tokend.sdk.api.accounts.model.UnifiedOperationRecord
import org.tokend.sdk.api.base.model.operations.TransferOperation

/**
 * Performs [UnifiedOperationRecord] to [TransferOperation] conversion
 */
interface UnifiedOperationRecordConverter {
    /**
     * Converts given [records] to the list of [TransferOperation].
     * Keep in mind that result list size may be different from the source.
     */
    fun toTransferOperations(records: Collection<UnifiedOperationRecord>): List<TransferOperation>
}