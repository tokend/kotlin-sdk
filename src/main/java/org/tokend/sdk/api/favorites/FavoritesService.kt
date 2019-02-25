package org.tokend.sdk.api.favorites

import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.favorites.model.FavoriteEntry
import retrofit2.Call
import retrofit2.http.*

interface FavoritesService {
    @POST("accounts/{account}/favorites")
    fun add(@Path("account") accountId: String,
            @Body entry: FavoriteEntry): Call<Void>

    @DELETE("accounts/{account}/favorites/{favorite}")
    fun delete(@Path("account") accountId: String,
               @Path("favorite") favoriteId: Long): Call<Void>

    @GET("accounts/{account}/favorites")
    @JvmSuppressWildcards
    fun get(@Path("account") accountId: String,
            @QueryMap query: Map<String, Any>): Call<DataEntity<List<FavoriteEntry>>>
}