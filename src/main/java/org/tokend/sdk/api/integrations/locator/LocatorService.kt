package org.tokend.sdk.api.integrations.locator

import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.integrations.locator.model.NearbyUser
import org.tokend.sdk.api.integrations.locator.model.NearbyUserWithCards
import org.tokend.sdk.api.integrations.locator.model.NearbyUsersRequestData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LocatorService {
    @POST("integrations/locator")
    @JvmSuppressWildcards
    fun getUsersNearby(@Body body: DataEntity<NearbyUsersRequestData>):
            Call<DataEntity<List<NearbyUser>>>

    @POST("integrations/locator")
    @JvmSuppressWildcards
    fun getUsersWithCardsNearby(@Body body: DataEntity<NearbyUsersRequestData>):
            Call<DataEntity<List<NearbyUserWithCards>>>
}