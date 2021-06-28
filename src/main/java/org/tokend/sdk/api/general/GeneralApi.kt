package org.tokend.sdk.api.general

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.general.model.SystemInfo

open class GeneralApi(
        protected val generalService: GeneralService
) {
    @Deprecated("Use GeneralApiV3.getSystemInfo instead")
    open fun getSystemInfo(): ApiRequest<SystemInfo> {
        return SimpleRetrofitApiRequest(
                generalService.getSystemInfo()
        )
    }
}