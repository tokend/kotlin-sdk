package org.tokend.sdk.api.v2.base

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.jasminb.jsonapi.JSONAPIDocument

/**
 * Holds pagination metadata
 */
class PaginationMeta(
        @JsonProperty(KEY_CURRENT_PAGE)
        val currentPage: Int,
        @JsonProperty(KEY_TOTAL_PAGES)
        val totalPages: Int
) {
    val isLast = currentPage == totalPages

    companion object {
        private const val KEY_CURRENT_PAGE = "current_page"
        private const val KEY_TOTAL_PAGES = "total_pages"

        /**
         * Creates [PaginationMeta] from [JSONAPIDocument.meta] of specific format.
         *
         * @throws IllegalArgumentException if required pages are missing or invalid
         */
        @JvmStatic
        fun fromMap(metaMap: Map<String, *>): PaginationMeta {
            val currentPage = (metaMap[KEY_CURRENT_PAGE] as? Int)
                    ?: throw IllegalArgumentException("Invalid or missing $KEY_CURRENT_PAGE")

            val totalPages = (metaMap[KEY_TOTAL_PAGES] as? Int)
                    ?: throw IllegalArgumentException("Invalid or missing $KEY_TOTAL_PAGES")

            return PaginationMeta(currentPage, totalPages)
        }
    }
}