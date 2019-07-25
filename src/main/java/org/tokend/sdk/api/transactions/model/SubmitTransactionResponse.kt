package org.tokend.sdk.api.transactions.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import java.util.*

open class SubmitTransactionResponse constructor(@SerializedName("extras")
                                                 val extras: Extras?,
                                                 @SerializedName("ledger")
                                                 val ledger: Long?,
                                                 @SerializedName("created_at")
                                                 val createdAt: Date?,
                                                 @SerializedName("hash")
                                                 val hash: String?,
                                                 @SerializedName("envelope_xdr")
                                                 private val envelopeXdr: String,
                                                 @SerializedName("result_xdr")
                                                 private val resultXdr: String) {

    val isSuccess: Boolean
        get() = ledger != null

    fun getEnvelopeXdr(): String {
        return if (this.isSuccess) {
            this.envelopeXdr
        } else {
            this.extras!!.envelopeXdr
        }
    }

    fun getResultXdr(): String? {
        return if (this.isSuccess) {
            this.resultXdr
        } else {
            this.extras!!.resultXdr
        }
    }

    open class Extras internal constructor(@SerializedName("envelope_xdr")
                                           val envelopeXdr: String,
                                           @SerializedName("result_xdr")
                                           val resultXdr: String,
                                           @SerializedName("result_codes")
                                           val resultCodes: ResultCodes,
                                           @SerializedName("parsed_result")
                                           val parsedResultJson: JsonObject) {


        open class ResultCodes(@SerializedName("transaction")
                               val transactionResultCode: String,
                               @SerializedName("operations")
                               val operationsResultCodes: ArrayList<String>)
    }
}
