package org.tokend.sdk.api.requests.model.withdrawal

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class WithdrawalDetails(
        @SerializedName("balance_id")
        val balanceId: String,
        @SerializedName("amount")
        val amount: BigDecimal,
        @SerializedName("fixed_fee")
        val fixedFee: BigDecimal,
        @SerializedName("percent_fee")
        val percentFee: BigDecimal,
        @SerializedName("pre_confirmation_details")
        val preConfirmationDetails: JsonElement,
        @SerializedName("external_details")
        val externalDetails: JsonElement,
        @SerializedName("reviewer_details")
        val reviewerDetails: JsonElement,
        @SerializedName("dest_asset_code")
        val destAsset: String,
        @SerializedName("dest_asset_amount")
        val destAmount: BigDecimal
)