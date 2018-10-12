package org.tokend.sdk.api.general

import org.tokend.sdk.api.general.model.SystemInfo
import retrofit2.Call
import retrofit2.http.GET

interface GeneralService {
    @GET(".")
    fun getSystemInfo(): Call<SystemInfo>
}