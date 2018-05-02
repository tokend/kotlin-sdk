package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

/**
 * Created by Oleg Koretsky on 1/4/18.
 */
data class Asset(
        @SerializedName("code") val code: String?,
        @SerializedName("owner") val ownerAccount: String?,
        @SerializedName("policy") val policy: Int?,
        @SerializedName("details") val details: Details?,
        @SerializedName("issued") val issued: BigDecimal?,
        @SerializedName("sales") val sales: List<Sale>? = null) {

    class Details(
            @SerializedName("name") val name: String?,
            @SerializedName("logo") val logo: RemoteFile?,
            @SerializedName("terms") val terms: RemoteFile?
    )
}