package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.math.BigDecimal

open class Asset(
        @SerializedName("code") val code: String,
        @SerializedName("owner") val ownerAccount: String,
        @SerializedName("policy") val policy: Int,
        @SerializedName("details") val details: Details?,
        @SerializedName("issued") val issued: BigDecimal,
        @SerializedName("available_for_issuance") val available: BigDecimal,
        @SerializedName("max_issuance_amount") val maximum: BigDecimal,
        @SerializedName("sales") open val sales: List<Sale>? = null) : Serializable {

    open class Details(
            @SerializedName("name") val name: String,
            @SerializedName("logo") open val logo: RemoteFile,
            @SerializedName("terms") open val terms: RemoteFile,
            @SerializedName("external_system_type") protected open val externalSystemTypeString: String?
    ) : Serializable {
        open val externalSystemType: Int?
            get() = externalSystemTypeString?.toIntOrNull()
    }

    open val isBackedByExternalSystem: Boolean
        get() = details?.externalSystemType != null
}