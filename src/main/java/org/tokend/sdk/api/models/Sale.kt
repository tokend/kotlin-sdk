package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.ApiDateUtil
import org.tokend.sdk.utils.BigDecimalUtil
import java.io.Serializable
import java.math.BigDecimal
import java.util.*

open class Sale(@SerializedName("id")
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
                private val startTimeString: String? = null,
                @SerializedName("end_time")
                private val endTimeString: String? = null,
                @SerializedName("soft_cap")
                val softCapString: String? = null,
                @SerializedName("hard_cap")
                val hardCapString: String? = null,
                @SerializedName("current_cap")
                val currentCapString: String? = null,
                @SerializedName("base_hard_cap")
                val baseHardCapString: String? = null,
                @SerializedName("base_current_cap")
                val baseCurrentCapString: String? = null,
                @SerializedName("details")
                open val details: Details? = null,
                @SerializedName("state")
                protected open val mState: State? = null,
                @SerializedName("sale_type")
                protected open val mType: Type? = null,
                @SerializedName("statistics")
                open val statistics: Statistics? = null,
                open var ownerDetails: AccountsDetailsResponse.AccountDetails? = null
) : Serializable {

    open class Details(@SerializedName("name")
                       val name: String? = null,
                       @SerializedName("short_description")
                       val shortDescription: String? = null,
                       @SerializedName("description")
                       val description: String? = null,
                       @SerializedName("logo")
                       open val logo: RemoteFile? = null,
                       @SerializedName("youtube_video_id")
                       val youtubeVideoId: String? = null) : Serializable {

        open val youtubeVideoPreviewImage: String?
            get() =
                if (youtubeVideoId != null)
                    "https://img.youtube.com/vi/$youtubeVideoId/hqdefault.jpg"
                else null

        open val youtubeVideoUrl: String?
            get() =
                if (youtubeVideoId != null)
                    "http://m.youtube.com/watch?v=$youtubeVideoId"
                else null
    }

    open class State(@SerializedName("value")
                     val value: Int? = null) : Serializable {

        companion object {
            val OPEN = 1
            val CLOSED = 2
            val CANCELED = 4
        }
    }

    open class Type(@SerializedName("value")
                    val value: Int? = null) : Serializable

    open class Statistics(@SerializedName("investors")
                          val investors: Int? = null) : Serializable

    protected open class QuoteAssets(@SerializedName("quote_assets")
                                     open val items: List<QuoteAsset>? = null) : Serializable

    open class QuoteAsset(@SerializedName("asset")
                          val code: String,
                          @SerializedName("price")
                          private val priceString: String? = null,
                          @SerializedName("current_cap")
                          private val currentCapString: String? = null,
                          @SerializedName("total_current_cap")
                          private val totalCurrentCapString: String? = null,
                          @SerializedName("hard_cap")
                          private val hardCapString: String? = null) {

        open val price: BigDecimal
            get() = BigDecimalUtil.valueOf(priceString)
        open val currentCap: BigDecimal
            get() = BigDecimalUtil.valueOf(currentCapString)
        open val totalCurrentCap: BigDecimal
            get() = BigDecimalUtil.valueOf(totalCurrentCapString)
        open val hardCap: BigDecimal
            get() = BigDecimalUtil.valueOf(hardCapString)
    }

    @SerializedName("quote_assets")
    protected open val mQuoteAssets: QuoteAssets? = null

    open val startDate: Date
        get() = ApiDateUtil.tryParseDate(this.startTimeString)
    open val endDate: Date
        get() = ApiDateUtil.tryParseDate(this.endTimeString)

    open val isAvailable: Boolean
        get() = !isUpcoming && !isEnded

    open val isUpcoming: Boolean
        get() = startDate.after(Date())

    open val isEnded: Boolean
        get() = isClosed || isCanceled

    open val isClosed: Boolean
        get() = state == State.CLOSED

    open val isCanceled: Boolean
        get() = state == State.CANCELED

    open val state: Int?
        get() = mState?.value

    open val type: Int?
        get() = mType?.value

    open val quoteAssets: List<QuoteAsset>?
        get() = mQuoteAssets?.items

    open val hardCap: BigDecimal
        get() = BigDecimalUtil.valueOf(hardCapString)
    open val softCap: BigDecimal
        get() = BigDecimalUtil.valueOf(softCapString)
    open val currentCap: BigDecimal
        get() = BigDecimalUtil.valueOf(currentCapString)
    open val baseHardCap: BigDecimal
        get() = BigDecimalUtil.valueOf(baseHardCapString)
    open val baseCurrentCap: BigDecimal
        get() = BigDecimalUtil.valueOf(baseCurrentCapString)
}