package org.tokend.sdk.api.requests.model.asset

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.requests.model.base.ReviewableRequestDetails

open class AssetRequestDetails<AssetType>(
        typeI: Int,
        @SerializedName("create_asset")
        val assetCreate: AssetType?,
        @SerializedName("update_asset")
        val assetUpdate: AssetType?
) : ReviewableRequestDetails(typeI)