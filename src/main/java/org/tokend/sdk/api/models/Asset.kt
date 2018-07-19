package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.HashCodes
import java.io.Serializable
import java.math.BigDecimal

open class Asset<SaleType>(
        @SerializedName("code") val code: String,
        @SerializedName("owner") val ownerAccount: String,
        @SerializedName("policy") val policy: Int,
        @SerializedName("details") val details: Details?,
        @SerializedName("issued") val issued: BigDecimal,
        @SerializedName("available_for_issuance") val available: BigDecimal,
        @SerializedName("max_issuance_amount") val maximum: BigDecimal,
        @SerializedName("sales") open val sales: List<SaleType>? = null) : Serializable {

    open class Details(
            @SerializedName("name") val name: String,
            @SerializedName("logo") open val logo: RemoteFile,
            @SerializedName("terms") open val terms: RemoteFile,
            @SerializedName("external_system_type") protected open val externalSystemTypeString: String?
    ) : Serializable {
        open val externalSystemType: Int?
            get() = externalSystemTypeString?.toIntOrNull()

        override fun equals(other: Any?): Boolean {
            return other is Details
                    && other.name == this.name
                    && other.terms == this.terms
                    && other.logo == logo
                    && other.externalSystemType == other.externalSystemType
        }

        override fun hashCode(): Int {
            return HashCodes.ofMany(name, terms, logo, externalSystemType)
        }
    }

    open val isBackedByExternalSystem: Boolean
        get() = details?.externalSystemType != null

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