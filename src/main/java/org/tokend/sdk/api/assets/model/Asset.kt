package org.tokend.sdk.api.assets.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.math.BigDecimal

/**
 * @see org.tokend.sdk.api.generated.resources.AssetResource
 */
@Deprecated("We are going to replace with AssetResource")
open class Asset<DetailsType : AssetDetails>(
        @SerializedName("code") val code: String,
        @SerializedName("owner") val ownerAccount: String?,
        @SerializedName("policy") val policy: Int,
        @SerializedName("details") val details: DetailsType,
        @SerializedName("issued") val issued: BigDecimal?,
        @SerializedName("available_for_issuance") val available: BigDecimal?,
        @SerializedName("max_issuance_amount") val maximum: BigDecimal) : Serializable {

    open val isBackedByExternalSystem: Boolean
        get() = details.externalSystemType != null

    override fun equals(other: Any?): Boolean {
        return other is Asset<*>
                && other.code == this.code
    }

    override fun hashCode(): Int {
        return code.hashCode()
    }
}