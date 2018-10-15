package org.tokend.sdk.api.accounts.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.base.model.NameValue
import java.math.BigDecimal
import java.util.*


open class Account(
        @SerializedName("account_id")
        val accountId: String,
        @SerializedName("paging_token")
        val pagingToken: String,
        @SerializedName("subentry_count")
        val subentryCount: Int,
        @SerializedName("is_blocked")
        val isBlocked: Boolean,
        @SerializedName("account_type_i")
        val typeI: Int,
        @SerializedName("block_reasons_i")
        val block_reasons: Int,
        @SerializedName("account_type")
        private val typeString: String,
        @SerializedName("thresholds")
        val thresholds: Thresholds,
        @SerializedName("balances")
        val balances: MutableList<Balance>,
        @SerializedName("signers")
        val signers: List<Signer>,
        @SerializedName("limits")
        val limits: Limits,
        @SerializedName("statistics")
        val statistics: Statistics,
        @SerializedName("external_system_accounts")
        val externalAccounts: List<ExternalAccount>,
        @SerializedName("referrals")
        val referrals: List<Referral>,
        @SerializedName("referrer")
        val referrer: String
) {
    companion object {
        const val BLOCK_REASON_RECOVERY_REQUEST = 1
        const val BLOCK_REASON_KYC_UPDATE = 2
        const val BLOCK_REASON_SUSPICIOUS_BEHAVIOR = 4
    }

    open class Limits {
        @SerializedName("annual_out")
        val annualOut: BigDecimal = BigDecimal.ZERO
        @SerializedName("daily_out")
        val dailyOut: BigDecimal = BigDecimal.ZERO
        @SerializedName("monthly_out")
        val mounthlyOut: BigDecimal = BigDecimal.ZERO
        @SerializedName("weekly_out")
        val weeklyOut: BigDecimal = BigDecimal.ZERO
    }

    open class Statistics {
        @SerializedName("daily_outcome")
        val dailyOut: BigDecimal = BigDecimal.ZERO
        @SerializedName("weekly_outcome")
        val weeklyOut: BigDecimal = BigDecimal.ZERO
        @SerializedName("monthly_outcome")
        val mounthlyOut: BigDecimal = BigDecimal.ZERO
        @SerializedName("annual_outcome")
        val annualOut: BigDecimal = BigDecimal.ZERO
    }

    open class ExternalAccount(@SerializedName("data")
                               val data: String,
                               @SerializedName("type")
                               val type: NameValue<Int>,
                               @SerializedName("expires_at")
                               val expirationDate: Date?)

    open class Referral(@SerializedName("account_id")
                        val accountId: String? = null,
                        @SerializedName("account_type_i")
                        val typeI: Int? = null)

    /**
     * Represents account thresholds.
     */
    open class Thresholds internal constructor(@SerializedName("low_threshold")
                                               val lowThreshold: Int,
                                               @SerializedName("med_threshold")
                                               val medThreshold: Int,
                                               @SerializedName("high_threshold")
                                               val highThreshold: Int)

    /**
     * Represents account balance.
     */
    open class Balance internal constructor(@SerializedName("balance_id")
                                            val balanceId: String,
                                            @SerializedName("balance")
                                            var balanceString: String,
                                            @SerializedName("exchange_id")
                                            val exchangeId: String,
                                            @SerializedName("exchange_name")
                                            val exchangeName: String,
                                            @SerializedName("asset")
                                            val asset: String,
                                            @SerializedName("locked")
                                            var lockedString: String,
                                            @SerializedName("incentive_per_coin")
                                            val incentive: String)

    /**
     * Represents account signers.
     */
    open class Signer(accountId: String, weight: Int,
                      type: Int, identity: Int,
                      @SerializedName("signer_name")
                      val name: String = "") {

        constructor(accountId: String) : this(accountId, 255, Int.MAX_VALUE, 1)

        @SerializedName("public_key")
        val accountId: String = checkNotNull(accountId, { "accountId cannot be null" })
        @SerializedName("weight")
        val weight: Int = checkNotNull(weight, { "weight cannot be null" })
        @SerializedName("signer_type_i")
        val type: Int = checkNotNull(type, { "type cannot be null" })
        @SerializedName("signer_identity")
        val identity: Int = checkNotNull(identity, { "weight cannot be null" })
    }
}
