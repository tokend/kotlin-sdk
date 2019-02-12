package org.tokend.sdk.api.assets.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.base.model.RemoteFile
import org.tokend.sdk.utils.HashCodes
import java.io.Serializable

@Deprecated("Asset details are system-specific and will be removed from SDK")
open class AssetDetails(
        @SerializedName("name")
        @JsonProperty("name")
        val name: String,

        @SerializedName("logo")
        @JsonProperty("logo")
        open val logo: RemoteFile?,

        @SerializedName("terms")
        @JsonProperty("terms")
        open val terms: RemoteFile?,

        @SerializedName("external_system_type")
        @JsonProperty("external_system_type")
        protected open val externalSystemTypeString: String?
) : Serializable {
    open val externalSystemType: Int?
        get() = externalSystemTypeString?.toIntOrNull()

    override fun equals(other: Any?): Boolean {
        return other is AssetDetails
                && other.name == this.name
                && other.terms == this.terms
                && other.logo == logo
                && other.externalSystemType == other.externalSystemType
    }

    override fun hashCode(): Int {
        return HashCodes.ofMany(name, terms, logo, externalSystemType)
    }
}