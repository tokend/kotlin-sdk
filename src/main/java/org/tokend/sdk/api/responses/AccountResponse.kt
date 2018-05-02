package org.tokend.sdk.api.responses

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.util.*
import kotlin.collections.HashMap


class AccountResponse : Response(), TransactionBuilderAccount {
    companion object {
        val BLOCK_REASON_RECOVERY_REQUEST = 1
        val BLOCK_REASON_KYC_UPDATE = 2
        val BLOCK_REASON_SUSPICIOUS_BEHAVIOR = 4
    }

    enum class LimitType(val key: String) {
        DAILY_OUT("daily_out"),
        WEEKLY_OUT("weekly_out"),
        MONTHLY_OUT("monthly_out"),
        ANNUAL_OUT("annual_out")
    }

    enum class StatisticType(val key: String) {
        DAILY_OUTCOME("daily_outcome"),
        WEEKLY_OUTCOME("weekly_outcome"),
        MONTHLY_OUTCOME("monthly_outcome"),
        ANNUAL_OUTCOME("annual_outcome")
    }

    class ExternalAccount(@SerializedName("data")
                          val data: String? = null,
                          @SerializedName("type")
                          private val typeMap: HashMap<String, String> = hashMapOf()) {
    }

    class Referral(@SerializedName("account_id")
                   val accountId: String? = null,
                   @SerializedName("account_type_i")
                   val typeI: Int? = null)

    @SerializedName("account_id")
    /* KeyPairTypeAdapter used */
    private val accountId: String? = null
    @SerializedName("id")
    private val id: String? = null
    @SerializedName("sequence")
    private var sequenceNumber: Long? = Date().time / 1000
    @SerializedName("paging_token")
    val pagingToken: String? = null
    @SerializedName("subentry_count")
    val subentryCount: Int? = null
    @SerializedName("is_blocked")
    val isBlocked: Boolean = false
    @SerializedName("account_type_i")
    private val accountTypeI: Int = 0
    @SerializedName("block_reasons_i")
    val block_reasons: Int = 0
    @SerializedName("account_type")
    private val accountType: String? = null
    @SerializedName("incentive_per_coin_expires_at")
    val buyNowExpiresAt: Long? = null
    @SerializedName("thresholds")
    val thresholds: Thresholds? = null
    @SerializedName("flags")
    val flags: Flags? = null
    @SerializedName("balances")
    var balances: MutableList<Balance>? = null
        private set
    @SerializedName("signers")
    val signers: List<Signer>? = null
    @SerializedName("_links")
    val links: Links? = null
    @SerializedName("limits")
    private var limits: HashMap<String, String> = HashMap()
    @SerializedName("statistics")
    private var statistics: HashMap<String, String> = HashMap()
    @SerializedName("external_system_accounts")
    val externalAccounts: List<ExternalAccount> = listOf()
    @SerializedName("referrals")
    val referrals: List<Referral> = listOf()

    override fun getAccountId() = accountId

    override fun getSequenceNumber() = sequenceNumber

    override fun getIncrementedSequenceNumber() = sequenceNumber!! + 1

    override fun incrementSequenceNumber() {
        sequenceNumber?.plus(1)
    }

    fun getLimit(limitType: LimitType): BigDecimal {
        return BigDecimal(limits[limitType.key] ?: "200")
    }

    fun getStatistic(statType: StatisticType): BigDecimal {
        return BigDecimal(statistics[statType.key] ?: "5")
    }

    fun getAssetBalance(asset: String): BigDecimal {
        return BigDecimal(balances?.find { it.asset == asset }?.balance ?: "0")
    }

    fun getAssetLocked(asset: String): BigDecimal {
        return BigDecimal(balances?.find { it.asset == asset }?.locked ?: "0")
    }

    fun getAssetBalanceId(asset: String): String? {
        return balances?.find { it.asset == asset }?.balanceId
    }

    fun addBalance(asset: String, balanceId: String?, balance: String?, locked: String?) {
        balances?.add(Balance(asset = asset, balanceId = balanceId, balance = balance, locked = locked))
    }

    /**
     * Represents account thresholds.
     */
    class Thresholds internal constructor(@SerializedName("low_threshold")
                                          val lowThreshold: Int,
                                          @SerializedName("med_threshold")
                                          val medThreshold: Int,
                                          @SerializedName("high_threshold")
                                          val highThreshold: Int)

    /**
     * Represents account flags.
     */
    class Flags internal constructor(@SerializedName("auth_required")
                                     val authRequired: Boolean,
                                     @SerializedName("auth_revocable")
                                     val authRevocable: Boolean)

    /**
     * Represents account balance.
     */
    class Balance internal constructor(@SerializedName("balance_id")
                                       val balanceId: String?,
                                       @SerializedName("balance")
                                       var balance: String?,
                                       @SerializedName("exchange_id")
                                       val exchangeId: String? = null,
                                       @SerializedName("exchange_name")
                                       val exchangeName: String? = null,
                                       @SerializedName("asset")
                                       val asset: String,
                                       @SerializedName("locked")
                                       var locked: String?,
                                       @SerializedName("incentive_per_coin")
                                       val incentive: String? = null)

    /**
     * Represents account signers.
     */
    class Signer(accountId: String, weight: Int,
                 type: Int, identity: Int) {

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

    /**
     * Links connected to account.
     */
    class Links internal constructor(@SerializedName("effects")
                                     val effects: Link, @SerializedName("offers")
                                     val offers: Link, @SerializedName("operations")
                                     val operations: Link, @SerializedName("self")
                                     val self: Link, @SerializedName("transactions")
                                     val transactions: Link)
}
