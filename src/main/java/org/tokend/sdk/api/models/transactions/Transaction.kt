package org.tokend.sdk.api.models.transactions

import org.tokend.sdk.api.models.Asset
import org.tokend.sdk.api.models.Participant
import org.tokend.sdk.api.models.PaymentRecord
import org.tokend.sdk.utils.ApiDateUtil
import java.util.*

open class Transaction @JvmOverloads constructor(val amount: String? = null,
                                                 private var assetString: String? = null,
                                                 val from: String? = null,
                                                 val to: String? = null,
                                                 val createdAt: String? = null,
                                                 val sourceFixedFee: String? = null,
                                                 val sourcePaymentFee: String? = null,
                                                 val type: String? = null,
                                                 var state: PaymentState? = null,
                                                 val id: String? = null,
                                                 val isFeeFromSource: Boolean = false,
                                                 val paymentType: String? = null,
                                                 val subject: String? = null,
                                                 val participants: List<Participant>?,
                                                 val fromBalance: String?,
                                                 val toBalance: String?,
                                                 val pagingToken: String?,
                                                 val destFixedFee: String? = null,
                                                 val destPaymentFee: String? = null,
                                                 var fromNickname: String? = null,
                                                 var toNickname: String? = null) {
    enum class PaymentState {
        PENDING, SUCCESS, REJECTED, CANCELED;

        companion object {
            fun fromCode(code: Int?): PaymentState {
                return when (code) {
                    2 -> SUCCESS
                    3 -> REJECTED
                    4 -> CANCELED
                    else -> PENDING
                }
            }
        }
    }

    open fun isReceived(accountId: String) = accountId == this.to

    open fun isSent(accountId: String) = !isReceived(accountId)

    val date: Date
        get() = ApiDateUtil.tryParseDate(this.createdAt)

    val asset: String
        get() = assetString ?: Asset.DEFAULT_ASSET

    companion object {
        const val TYPE_PAYMENT = "payment"
        const val PAYMENT_TYPE_TRANSFER = "Transfer"
        const val PAYMENT_TYPE_INVOICE = "Invoice"
        const val PAYMENT_TYPE_GIFT = "Gift"

        fun fromRecord(record: PaymentRecord): Transaction {
            return Transaction(record.amount, record.asset,
                    record.from, record.to, record.ledgerCloseTime,
                    record.sourceFixedFee, record.sourcePaymentFee,
                    record.type, PaymentState.fromCode(record.state), record.id,
                    record.isFeeFromSource,
                    record.paymentType, record.subject, record.participants, record.fromBalance,
                    record.toBalance!!,
                    record.pagingToken,
                    record.destFixedFee, record.destPaymentFee)
        }
    }
}

