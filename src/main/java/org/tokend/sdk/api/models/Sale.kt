package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.ApiDateUtil
import org.tokend.sdk.utils.BigDecimalUtil
import java.math.BigDecimal
import java.util.*

open class Sale(@SerializedName("id")
           val id: Long? = null,
           @SerializedName("paging_token")
           val pagingToken: String? = null,
           @SerializedName("owner_id")
           val ownerAccount: String? = null,
           @SerializedName("base_asset")
           val baseAsset: String? = null,
           @SerializedName("default_quote_asset")
           val defaultQuoteAsset: String? = null,
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
           val details: Details? = null,
           @SerializedName("state")
           private val mState: State? = null,
           @SerializedName("sale_type")
           private val mType: Type? = null,
           @SerializedName("statistics")
           val statistics: Statistics? = null,
           var ownerDetails: AccountsDetailsResponse.AccountDetails? = null) {

    class Details(@SerializedName("name")
                  val name: String? = null,
                  @SerializedName("short_description")
                  val shortDescription: String? = null,
                  @SerializedName("description")
                  val description: String? = null,
                  @SerializedName("logo")
                  val logo: RemoteFile? = null,
                  @SerializedName("youtube_video_id")
                  val youtubeVideoId: String? = null) {

        val youtubeVideoPreviewImage: String?
            get() =
                if (youtubeVideoId != null)
                    "https://img.youtube.com/vi/$youtubeVideoId/hqdefault.jpg"
                else null

        val youtubeVideoUrl: String?
            get() =
                if (youtubeVideoId != null)
                    "http://m.youtube.com/watch?v=$youtubeVideoId"
                else null
    }

    class State(@SerializedName("value")
                val value: Int? = null) {

        companion object {
            val OPEN = 1
            val CLOSED = 2
            val CANCELED = 4
        }
    }

    class Type(@SerializedName("value")
               val value: Int? = null)

    class Statistics(@SerializedName("investors")
                     val investors: Int? = null)

    private class QuoteAssets(@SerializedName("quote_assets")
                              val items: List<QuoteAsset>? = null)

    class QuoteAsset(@SerializedName("asset")
                     val code: String? = null,
                     @SerializedName("price")
                     private val priceString: String? = null,
                     @SerializedName("current_cap")
                     private val currentCapString: String? = null,
                     @SerializedName("total_current_cap")
                     private val totalCurrentCapString: String? = null,
                     @SerializedName("hard_cap")
                     private val hardCapString: String? = null) {

        val price: BigDecimal
            get() = BigDecimalUtil.valueOf(priceString)
        val currentCap: BigDecimal
            get() = BigDecimalUtil.valueOf(currentCapString)
        val totalCurrentCap: BigDecimal
            get() = BigDecimalUtil.valueOf(totalCurrentCapString)
        val hardCap: BigDecimal
            get() = BigDecimalUtil.valueOf(hardCapString)
    }

    @SerializedName("quote_assets")
    private val mQuoteAssets: QuoteAssets? = null

    val startDate: Date
        get() = ApiDateUtil.tryParseDate(this.startTimeString)
    val endDate: Date
        get() = ApiDateUtil.tryParseDate(this.endTimeString)

    val isAvailable: Boolean
        get() = !isUpcoming && !isEnded

    val isUpcoming: Boolean
        get() = startDate.after(Date())

    val isEnded: Boolean
        get() = isClosed || isCanceled

    val isClosed: Boolean
        get() = state == State.CLOSED

    val isCanceled: Boolean
        get() = state == State.CANCELED

    val state: Int?
        get() = mState?.value

    val type: Int?
        get() = mType?.value

    val quoteAssets: List<QuoteAsset>?
        get() = mQuoteAssets?.items

    val hardCap: BigDecimal
        get() = BigDecimalUtil.valueOf(hardCapString)
    val softCap: BigDecimal
        get() = BigDecimalUtil.valueOf(softCapString)
    val currentCap: BigDecimal
        get() = BigDecimalUtil.valueOf(currentCapString)
    val baseHardCap: BigDecimal
        get() = BigDecimalUtil.valueOf(baseHardCapString)
    val baseCurrentCap: BigDecimal
        get() = BigDecimalUtil.valueOf(baseCurrentCapString)
}