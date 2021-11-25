package org.tokend.sdk.api.v3.general

import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.generated.resources.HorizonStateResource
import org.tokend.sdk.utils.extentions.toNetworkParams

open class HorizonInfoApiV3(
    protected open val customRequestsApi: CustomRequestsApi
) {
    /**
     * @see HorizonStateResource.toNetworkParams
     */
    open fun getInfo() =
        customRequestsApi.get(
            url = "v3/info",
            responseClass = HorizonStateResource::class.java
        )
}