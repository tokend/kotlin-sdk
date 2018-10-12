package org.tokend.sdk.api.assets.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.models.RemoteFile
import org.tokend.sdk.utils.HashCodes
import java.io.Serializable

open class AssetDetails(
        @SerializedName("name") val name: String,
        @SerializedName("logo") open val logo: RemoteFile,
        @SerializedName("terms") open val terms: RemoteFile,
        @SerializedName("external_system_type") protected open val externalSystemTypeString: String?
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