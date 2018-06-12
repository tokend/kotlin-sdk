package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Oleg Koretsky on 1/8/18.
 */
open class Blob(
        @SerializedName("id") val id: String? = null,
        @SerializedName("type") val type: String? = null,
        @SerializedName("attributes") private val attributes: Attributes? = null) {

    companion object {
        const val TYPE_ASSET_DESCRIPTION = 1
        const val TYPE_FUND_OVERVIEW = 2
        const val TYPE_FUND_UPDATE = 4
        const val TYPE_NAV_UPDATE = 8
        const val TYPE_FUND_DOCUMENT = 16
        const val TYPE_SYNDICATE_KYC = 32
        const val TYPE_TOKEN_TERMS = 512
        const val TYPE_TOKEN_METRICS = 1024
    }

    class Attributes(@SerializedName("value") val value: String? = null)

    val valueString: String?
        get() = attributes?.value
}