package org.tokend.sdk.api.accounts.model.limits

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
    val entriesMap = entries
            .asSequence()
            .associateBy { it.limit.asset }

    /**
     * @return [LimitEntry] for given asset or [null] if it is not exists
     */
    fun getAssetEntry(asset: String): LimitEntry? {
        return entriesMap[asset]
    }

    /**
     * @return [Limit] for given asset or [null] if it is not exists
     */
    fun getAssetLimit(asset: String): Limit? {
        return getAssetEntry(asset)?.limit
    }

    /**
     * @return [Statistics] for given asset or [null] if it is not exists
     */
    fun getAssetStatistics(asset: String): Statistics? {
        return getAssetEntry(asset)?.statistics
    }
}