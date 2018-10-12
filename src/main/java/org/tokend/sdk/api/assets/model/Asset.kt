package org.tokend.sdk.api.assets.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.HashCodes
import java.io.Serializable
import java.math.BigDecimal

open class Asset<DetailsType : AssetDetails>(
        @SerializedName("code") val code: String,
        @SerializedName("owner") val ownerAccount: String,
        @SerializedName("policy") val policy: Int,
        @SerializedName("details") val details: DetailsType,
        @SerializedName("issued") val issued: BigDecimal,
        @SerializedName("available_for_issuance") val available: BigDecimal,
        @SerializedName("max_issuance_amount") val maximum: BigDecimal) : Serializable {

    open val isBackedByExternalSystem: Boolean
        get() = details.externalSystemType != null

    override fun equals(other: Any?): Boolean {
        return other is Asset<*>
                && other.code == this.code
                && other.issued == this.issued
                && other.policy == this.policy
                && other.details == this.details
    }

    override fun hashCode(): Int {
        return HashCodes.ofMany(code, policy, details, issued)
    }
}