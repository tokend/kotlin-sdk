package org.tokend.sdk.api.favorites

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.favorites.model.FavoriteEntry

open class FavoritesApi(
        protected val favoritesService: FavoritesService
) {
    open fun get(accountId: String): ApiRequest<List<FavoriteEntry>> {
        return MappedRetrofitApiRequest(
                favoritesService.get(accountId),
                { it.data }
        )
    }

    open fun add(accountId: String,
                 entry: FavoriteEntry): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
                favoritesService.add(
                        accountId,
                        entry
                )
        )
    }

    open fun delete(accountId: String,
                    entryId: Long): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
                favoritesService.delete(
                        accountId,
                        entryId
                )
        )
    }
}