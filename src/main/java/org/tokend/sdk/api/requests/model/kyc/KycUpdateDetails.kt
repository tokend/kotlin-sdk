package org.tokend.sdk.api.requests.model.kyc

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

class KycUpdateDetails(
        @SerializedName("account_to_update_kyc")
        val account: String,
        @SerializedName("account_type_to_set")
        val accountType: AccountType,
        @SerializedName("kyc_level")
        val kycLevelI: Int,
        @SerializedName("kyc_data")
        val kycData: JsonElement,
        @SerializedName("all_tasks")
        val allTasks: Int,
        @SerializedName("pending_tasks")
        val pendingTasks: Int,
        @SerializedName("sequence_number")
        val sequenceNumber: Int,
        @SerializedName("external_details")
        val externalDetails: JsonElement
) {
    constructor(account: String,
                accountType: org.tokend.wallet.xdr.AccountType,
                kycLevelI: Int,
                kycData: JsonElement,
                allTasks: Int,
                pendingTasks: Int,
                sequenceNumber: Int,
                externalDetails: JsonElement
    ) : this(account, AccountType(accountType.value, accountType.name.toLowerCase()),
            kycLevelI, kycData, allTasks, pendingTasks, sequenceNumber, externalDetails)

    class AccountType(
            @SerializedName("int")
            val int: Int,
            @SerializedName("string")
            val string: String
    )

    val accountTypeXdr: org.tokend.wallet.xdr.AccountType
        get() = org.tokend.wallet.xdr.AccountType.values().find { it.value == accountType.int }!!
}