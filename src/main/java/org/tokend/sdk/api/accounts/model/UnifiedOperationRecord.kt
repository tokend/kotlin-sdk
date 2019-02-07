package org.tokend.sdk.api.accounts.model

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.base.model.Page
import java.math.BigDecimal
import java.util.*

open class UnifiedOperationRecord {
    @SerializedName("_links")
    val links: Page.Links? = null
    @SerializedName("id")
    val id: String = ""
    @SerializedName("paging_token")
    val pagingToken: String = ""
    @SerializedName("source_account")
    val sourceAccount: String? = null
    @SerializedName("type")
    val type: String? = null
    @SerializedName("type_i")
    val typeI: Int? = null
    @SerializedName("state")
    val state: String? = null
    @SerializedName("identifier")
    val identifier: Long? = null
    @SerializedName("ledger_close_time")
    val ledgerCloseTime: Date = Date()
    @SerializedName("participants")
    val participants: List<Participant>? = null
    @SerializedName("from")
    val from: String? = null
    @SerializedName("to")
    val to: String? = null
    @SerializedName("from_balance")
    val fromBalance: String? = null
    @SerializedName("to_balance")
    val toBalance: String? = null
    @SerializedName("amount")
    val amount: BigDecimal? = null
    @SerializedName("source_pays_for_dest")
    val isFeeFromSource: Boolean = false
    @SerializedName("source_payment_fee")
    val sourcePaymentFee: BigDecimal? = null
    @SerializedName("destination_payment_fee")
    val destPaymentFee: BigDecimal? = null
    @SerializedName("source_fixed_fee")
    val sourceFixedFee: BigDecimal? = null
    @SerializedName("destination_fixed_fee")
    val destFixedFee: BigDecimal? = null
    @SerializedName("fee_percent")
    val percentFee: BigDecimal? = null
    @SerializedName("fee_fixed")
    val fixedFee: BigDecimal? = null
    @SerializedName("subject")
    val subject: String? = null
    @SerializedName("asset")
    val asset: String? = null
    @SerializedName("user_details")
    val userDetails: String? = null
    @SerializedName("receiver_balance")
    val receiverBalance: String? = null
    @SerializedName("sender")
    val sender: String? = null
    @SerializedName("reference")
    val reference: String? = null
    @SerializedName("external_details")
    val externalDetails: JsonElement? = null
    @SerializedName("dest_asset")
    val destAsset: String? = null
    @SerializedName("dest_amount")
    val destAmount: String? = null
    @SerializedName("source_fee_data")
    val sourceFeeData: FeeData? = null
    @SerializedName("destination_fee_data")
    val destFeeData: FeeData? = null

    class FeeData(
            @SerializedName("actual_payment_fee")
            val paymentFee: BigDecimal,
            @SerializedName("fixed_fee")
            val fixedFee: BigDecimal,
            @SerializedName("actual_payment_fee_asset_code")
            val asset: String
    )
}