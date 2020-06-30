package org.tokend.sdk.api.integrations.mergedhistory.params

import org.tokend.sdk.api.base.params.PagingParamsWithNamedCursors
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.v3.base.JsonApiQueryParams

class MergedHistoryPageParams
@JvmOverloads
constructor(
        val collections: Collection<String>? = null,
        val dimensionFilters: Map<String, String>? = null,
        val pagingParams: PagingParamsWithNamedCursors? = null,
        val participants: Collection<String>? = null,
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    override fun map(): Map<String, Any> = mutableMapOf<String, Any>().apply {
        putAll(pagingParams.map())
        putFilter("collections", collections?.joinToString(separator = ","))
        putFilter("participants", participants?.joinToString(separator = ","))
        dimensionFilters?.forEach { (dimensionFilterKey, dimensionFilterValue) ->
            putFilter(dimensionFilterKey, dimensionFilterValue)
        }
    }
}