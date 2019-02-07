package org.tokend.sdk.api.sales.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.accounts.model.AccountsDetailsResponse
import org.tokend.sdk.api.base.model.NameValue
import java.io.Serializable
import java.math.BigDecimal
import java.util.*

open class Sale<DetailsType, StatisticsType, QuoteAssetType>(@SerializedName("id")
                                                             val id: Long,
                                                             @SerializedName("paging_token")
                                                             val pagingToken: String,
                                                             @SerializedName("owner_id")
                                                             val ownerAccount: String,
                                                             @SerializedName("base_asset")
                                                             val baseAsset: String,
                                                             @SerializedName("default_quote_asset")
                                                             val defaultQuoteAsset: String,
                                                             @SerializedName("start_time")
                                                             val startDate: Date,
                                                             @SerializedName("end_time")
                                                             val endDate: Date,
                                                             @SerializedName("soft_cap")
                                                             val softCap: BigDecimal,
                                                             @SerializedName("hard_cap")
                                                             val hardCap: BigDecimal,
                                                             @SerializedName("current_cap")
                                                             val currentCap: BigDecimal,
                                                             @SerializedName("base_hard_cap")
                                                             val baseHardCap: BigDecimal,
                                                             @SerializedName("base_current_cap")
                                                             val baseCurrentCap: BigDecimal,
                                                             @SerializedName("details")
                                                             val details: DetailsType,
                                                             @SerializedName("state")
                                                             val state: NameValue<Int>,
                                                             @SerializedName("sale_type")
                                                             val type: NameValue<Int>,
                                                             @SerializedName("statistics")
                                                             val statistics: StatisticsType,
                                                             @SerializedName("quote_assets")
                                                             protected val mQuoteAssets: QuoteAssets<QuoteAssetType>,
                                                             var ownerDetails: AccountsDetailsResponse.AccountDetails? = null
) : Serializable {
    open class QuoteAssets<T>(@SerializedName("quote_assets")
                              open val items: List<T>) : Serializable

    open val isAvailable: Boolean
        get() = !isUpcoming && !isEnded

    open val isUpcoming: Boolean
        get() = startDate.after(Date())

    open val isEnded: Boolean
        get() = isClosed || isCanceled

    open val isClosed: Boolean
        get() = state.value == SaleStates.STATE_CLOSED

    open val isCanceled: Boolean
        get() = state.value == SaleStates.STATE_CANCELED

    open val quoteAssets: List<QuoteAssetType>
        get() = mQuoteAssets.items

    override fun equals(other: Any?): Boolean {
        return other is Sale<*, *, *>
                && other.id == this.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}