package org.tokend.sdk.api.integrations.locator

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.integrations.locator.model.*

open class LocatorApi(
        protected open val locatorService: LocatorService
) {
    /**
     * Makes you visible in the specified location
     * and returns users nearby.
     */
    open fun getUsersNearby(lat: Double, lng: Double,
                            radiusKm: Double,
                            accountId: String,
                            userData: MinimalUserData): ApiRequest<List<NearbyUser>> {
        return MappedRetrofitApiRequest(
                locatorService.getUsersNearby(DataEntity(
                        NearbyUsersRequestData(lat, lng, radiusKm, accountId, userData)
                )),
                DataEntity<List<NearbyUser>>::data
        )
    }

    /**
     * Makes you visible in the specified location
     * and returns users nearby.
     */
    open fun getUsersWithCardsNearby(lat: Double, lng: Double,
                                     radiusKm: Double,
                                     accountId: String,
                                     userData: MinimalUserDataWithCards
    ): ApiRequest<List<NearbyUserWithCards>> {
        return MappedRetrofitApiRequest(
                locatorService.getUsersWithCardsNearby(DataEntity(
                        NearbyUsersRequestData(lat, lng, radiusKm, accountId, userData)
                )),
                DataEntity<List<NearbyUserWithCards>>::data
        )
    }
}