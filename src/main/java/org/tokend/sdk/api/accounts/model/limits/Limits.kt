package org.tokend.sdk.api.accounts.model.limits

import org.tokend.wallet.xdr.StatsOpType

/**
 * Holds account's limits and statistics data.
 *
 * @see <a href="https://tokend.gitlab.io/docs/#get-account-limits">Docs</a>
 */
class Limits(
        val entries: List<LimitEntry>
) {
    /**
     * Entries by asset.
     */
    val entriesByAssetMap = entries
            .asSequence()
            .groupBy { it.limit.asset }

    /**
     * @return [Collection] of [LimitEntry] for given asset or [null] if it is not exists
     */
    fun getAssetEntries(asset: String): Collection<LimitEntry>? {
        return entriesByAssetMap[asset]
    }

    /**
     * @return [LimitEntry] for given asset and operation type or [null] if it is not exists
     */
    fun getAssetEntry(asset: String, operationTypeI: Int): LimitEntry? {
        return getAssetEntries(asset)?.find { it.limit.statsOpTypeI == operationTypeI }
    }

    /**
     * @return [LimitEntry] for given asset and operation type or [null] if it is not exists
     */
    fun getAssetEntry(asset: String, operationType: StatsOpType): LimitEntry? {
        return getAssetEntry(asset, operationType.value)
    }

    /**
     * @return [Limit] for given asset and operation type or [null] if it is not exists
     */
    fun getAssetLimit(asset: String, operationTypeI: Int): Limit? {
        return getAssetEntry(asset, operationTypeI)?.limit
    }

    /**
     * @return [Limit] for given asset and operation type or [null] if it is not exists
     */
    fun getAssetLimit(asset: String, operationType: StatsOpType): Limit? {
        return getAssetLimit(asset, operationType.value)
    }

    /**
     * @return [Statistics] for given asset and operation type or [null] if it is not exists
     */
    fun getAssetStatistics(asset: String, operationTypeI: Int): Statistics? {
        return getAssetEntry(asset, operationTypeI)?.statistics
    }

    /**
     * @return [Statistics] for given asset and operation type or [null] if it is not exists
     */
    fun getAssetStatistics(asset: String, operationType: StatsOpType): Statistics? {
        return getAssetStatistics(asset, operationType.value)
    }
}