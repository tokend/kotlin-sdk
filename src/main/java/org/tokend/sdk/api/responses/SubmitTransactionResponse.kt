package org.tokend.sdk.api.responses

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import java.util.*

class SubmitTransactionResponse internal constructor(@SerializedName("extras")
                                                     val extras: Extras,
                                                     @SerializedName("ledger")
                                                     val ledger: Long?,
                                                     @SerializedName("hash")
                                                     val hash: String,
                                                     @SerializedName("envelope_xdr")
                                                     private val envelopeXdr: String,
                                                     @SerializedName("result_xdr")
                                                     private val resultXdr: String) : Response() {

    val isSuccess: Boolean
        get() = ledger != null

    fun getEnvelopeXdr(): String {
        return if (this.isSuccess) {
            this.envelopeXdr
        } else {
            this.extras.envelopeXdr
        }
    }

    fun getResultXdr(): String? {
        return if (this.isSuccess) {
            this.resultXdr
        } else {
            val extras = this.extras
            extras.resultXdr
        }
    }

    class Extras internal constructor(@SerializedName("envelope_xdr")
                                      val envelopeXdr: String,
                                      @SerializedName("result_xdr")
                                      val resultXdr: String,
                                      @SerializedName("result_codes")
                                      val resultCodes: ResultCodes,
                                      @SerializedName("parsed_result")
                                      val parsedResultJson: JsonObject) {


        class ResultCodes(@SerializedName("transaction")
                          val transactionResultCode: String,
                          @SerializedName("operations")
                          val operationsResultCodes: ArrayList<String>)
    }
}
