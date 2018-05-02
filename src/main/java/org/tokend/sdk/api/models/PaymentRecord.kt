package org.tokend.sdk.api.models

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.models.transactions.Transaction
import org.tokend.sdk.api.responses.Page

/**
 * Created by Oleg Koretsky on 11/29/17.
 */
class PaymentRecord {
    @SerializedName("_links")
    var links: Page.Links? = null
    @SerializedName("id")
    var id: String? = null
    @SerializedName("paging_token")
    var pagingToken: String? = null
    @SerializedName("source_account")
    var sourceAccount: String? = null
    @SerializedName("type")
    var type: String? = null
    @SerializedName("type_i")
    var typeI: Int? = null
    @SerializedName("state_i")
    var state: Int? = null
    @SerializedName("identifier")
    var identifier: Long? = null
    @SerializedName("ledger_close_time")
    var ledgerCloseTime: String? = null
    @SerializedName("participants")
    val participants: List<Participant>? = null
    @SerializedName("from")
    var from: String? = null
    @SerializedName("to")
    var to: String? = null
    @SerializedName("from_balance")
    var fromBalance: String? = null
    @SerializedName("to_balance")
    var toBalance: String? = null
    @SerializedName("amount")
    var amount: String? = null
    @SerializedName("source_pays_for_dest")
    var isFeeFromSource: Boolean = false
    @SerializedName("source_payment_fee")
    val sourcePaymentFee: String? = null
    @SerializedName("destination_payment_fee")
    val destPaymentFee: String? = null
    @SerializedName("source_fixed_fee")
    val sourceFixedFee: String? = null
    @SerializedName("destination_fixed_fee")
    val destFixedFee: String? = null
    @SerializedName("fee_percent")
    val percentFee: String? = null
    @SerializedName("fee_fixed")
    val fixedFee: String? = null
    @SerializedName("subject")
    val subjectRaw: String? = null
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

    val subject: String
        get() {
            if (subjectRaw == null || subjectRaw.isEmpty()) {
                return ""
            }
            return if (subjectRaw.contains("in: ") || subjectRaw.contains("gf: ")) {
                if (subjectRaw.substring(0, 4) == "in: " || subjectRaw.substring(0, 4) == "gf: ") {
                    subjectRaw.substring(4, subjectRaw.length)
                } else {
                    ""
                }
            } else {
                subjectRaw
            }
        }
}