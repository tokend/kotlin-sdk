package org.tokend.sdk.api.favorites

import org.tokend.sdk.api.favorites.model.FavoriteEntry
import org.tokend.sdk.api.base.model.DataEntity
import retrofit2.Call
import retrofit2.http.*

interface FavoritesService {
    @POST("users/{accountId}/favorites")
    fun add(@Path("accountId") accountId: String,
            @Body entry: FavoriteEntry): Call<Void>

    @DELETE("users/{accountId}/favorites/{entryId}")
    fun delete(@Path("accountId") accountId: String,
               @Path("entryId") id: Long): Call<Void>

    @GET("users/{accountId}/favorites")
    fun get(@Path("accountId") accountId: String):
            Call<DataEntity<List<FavoriteEntry>>>
}