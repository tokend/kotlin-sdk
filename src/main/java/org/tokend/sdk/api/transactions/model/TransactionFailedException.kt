package org.tokend.sdk.api.transactions.model

class TransactionFailedException(val submitResponse: SubmitTransactionResponse) : Exception() {
    val transactionResultCode: String
        get() = submitResponse.extras!!.resultCodes.transactionResultCode
    val operationResultCodes: Collection<String>
        get() = submitResponse.extras!!.resultCodes.operationsResultCodes
    val firstOperationResultCode: String?
        get() = operationResultCodes.firstOrNull {
            it != "op_success"
        }

    override val message: String
        get() = "$transactionResultCode $operationResultCodes"

    companion object {
        const val TX_FAILED = "tx_failed"
        const val TX_BAD_AUTH = "tx_bad_auth_extra"
        const val TX_NO_ROLE_PERMISSION = "tx_no_role_permission"

        const val OP_LIMITS_EXCEEDED = "op_limits_exceeded"
        const val OP_INSUFFICIENT_BALANCE = "op_underfunded"
        const val OP_INVALID_AMOUNT = "op_invalid_amount"
        const val OP_MALFORMED = "op_malformed"
        const val OP_ACCOUNT_BLOCKED = "op_account_blocked"
        const val OP_INVALID_FEE = "op_fee_mismatched"
        const val OP_NOT_ALLOWED = "op_not_allowed"
        const val OP_HARD_CAP_VIOLATION = "op_order_violates_hard_cap"
        const val OP_INACTIVE_SALE = "op_sale_is_not_active"
        const val OP_ENDED_SALE = "op_sale_already_ended"
        const val OP_OFFER_CROSS_SELF = "op_cross_self"
        const val OP_NO_AVAILABLE_EXTERNAL_ACCOUNTS = "op_no_available_id"
        const val OP_AMOUNT_LESS_THEN_DEST_FEE = "op_payment_amount_is_less_than_dest_fee"
        const val OP_REQUIRES_KYC = "op_requires_kyc"
        const val OP_NOT_FOUND = "op_not_found"
        const val OP_NO_ROLE_PERMISSION = "op_no_role_permission"
        const val OP_NO_ENTRY = "op_no_entry"
    }
}