package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.math.BigDecimal

data class Asset(
        @SerializedName("code") val code: String,
        @SerializedName("owner") val ownerAccount: String,
        @SerializedName("policy") val policy: Int,
        @SerializedName("details") val details: Details?,
        @SerializedName("issued") val issued: BigDecimal,
        @SerializedName("available_for_issuance") val available: BigDecimal,
        @SerializedName("max_issuance_amount") val maximum: BigDecimal,
        @Transient
        @SerializedName("sales")
        val sales: List<Sale>? = null) : Serializable {

    data class Details(
            @SerializedName("name") val name: String,
            @SerializedName("logo") val logo: RemoteFile,
            @SerializedName("terms") val terms: RemoteFile,
            @SerializedName("external_system_type") val externalSystemType: Int?
    ) : Serializable

    val isBackedByExternalSystem: Boolean
        get() = details?.externalSystemType != null
}