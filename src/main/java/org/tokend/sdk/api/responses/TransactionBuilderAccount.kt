package org.tokend.sdk.api.responses


interface TransactionBuilderAccount {
    /**
     * Returns account id
     */
    fun getAccountId(): String?

    /**
     * Returns current sequence number ot this Account.
     */
    fun getSequenceNumber(): Long?

    /**
     * Returns sequence number incremented by one, but does not increment internal counter.
     */
    fun getIncrementedSequenceNumber(): Long?

    /**
     * Increments sequence number in this object by one.
     */
    fun incrementSequenceNumber()
}
