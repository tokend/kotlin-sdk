package org.tokend.sdk.api.requests.model.asset

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.requests.model.base.ReviewableRequestDetails

open class AssetRequestDetails<AssetType>(
        typeI: Int,
        @SerializedName("asset_create")
        val assetCreate: AssetType?,
        @SerializedName("asset_update")
        val assetUpdate: AssetType?
) : ReviewableRequestDetails(typeI)