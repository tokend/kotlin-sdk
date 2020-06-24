package org.tokend.sdk.api.base.params

/**
 * Query params for pagination over a joined collection
 * with multiple named cursors.
 *
 * @see cursorsQueryParams
 * @see getCursorsFromUrl
 */
class PagingParamsWithNamedCursors
@JvmOverloads
constructor(
        val order: PagingOrder? = null,
        val limit: Int? = null,
        cursorsByNames: Map<String, String?>? = null
) : QueryParams {
    private val cursorsQueryParams = cursorsByNames
            ?.filterValues { !it.isNullOrBlank() }
            ?.mapValues { it.value!! }
            ?.mapKeys { "page[cursor.${it.key}]" }
            ?: emptyMap()

    override fun map(): Map<String, Any> = mutableMapOf<String, Any>().apply {
        limit?.also { put(PagingParamsV2.QUERY_PARAM_LIMIT, it) }
        order?.also { put(PagingParamsV2.QUERY_PARAM_ORDER, it) }
        putAll(cursorsQueryParams)
    }

    companion object {
        private val CURSORS_REGEX = "page\\[cursor\\.(.+?)]=(.+?)(?:&|\$)".toRegex()

        fun getCursorsFromUrl(url: String): Map<String, String> {
            return CURSORS_REGEX.findAll(url)
                    .mapNotNull { matchResult ->
                        matchResult
                                .groupValues
                                .takeIf { it.size >= 2 }
                                ?.let { groups ->
                                    val name = groups[1]
                                    val value = groups.getOrNull(2)

                                    if (value != null)
                                        name to value
                                    else
                                        null
                                }
                    }
                    .toMap()
        }
    }
}