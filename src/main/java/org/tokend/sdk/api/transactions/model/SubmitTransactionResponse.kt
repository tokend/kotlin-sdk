package org.tokend.sdk.api.transactions.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import java.util.*

open class SubmitTransactionResponse constructor(
    @field:JsonProperty("extras")
    val extras: Extras?,
    @field:JsonProperty("ledger")
    val ledger: Long?,
    @field:JsonProperty("created_at")
    val createdAt: Date?,
    @field:JsonProperty("hash")
    val hash: String?,
    @field:JsonProperty("envelope_xdr")
    private val envelopeXdr: String,
    @field:JsonProperty("result_xdr")
    private val resultXdr: String,
    @field:JsonProperty("result_meta_xdr")
    val resultMetaXdr: String?
) {
    @get:JsonIgnore
    val isSuccess: Boolean
        get() = ledger != null

    @JsonIgnore
    fun getEnvelopeXdr(): String {
        return if (this.isSuccess) {
            this.envelopeXdr
        } else {
            this.extras!!.envelopeXdr
        }
    }

    @JsonIgnore
    fun getResultXdr(): String {
        return if (this.isSuccess) {
            this.resultXdr
        } else {
            this.extras!!.resultXdr
        }
    }

    open class Extras internal constructor(
        @JsonProperty("envelope_xdr")
        val envelopeXdr: String,
        @JsonProperty("result_xdr")
        val resultXdr: String,
        @JsonProperty("result_codes")
        val resultCodes: ResultCodes,
        @JsonProperty("parsed_result")
        val parsedResultJson: JsonNode
    ) {
        open class ResultCodes(
            @JsonProperty("transaction")
            val transactionResultCode: String,
            @JsonProperty("operations")
            val operationsResultCodes: ArrayList<String>
        )
    }
}
