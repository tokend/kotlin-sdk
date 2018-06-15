package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.ApiDateUtil
import java.math.BigDecimal
import java.util.*

open class AssetChartData(
        @SerializedName("hour") val hour: List<ChartPoint>? = null,
        @SerializedName("day") val day: List<ChartPoint>? = null,
        @SerializedName("week") val week: List<ChartPoint>? = null,
        @SerializedName("month") val month: List<ChartPoint>? = null,
        @SerializedName("year") val year: List<ChartPoint>? = null) {

    open class ChartPoint(@SerializedName("value") val value: BigDecimal? = null,
                     @SerializedName("timestamp") private val timestamp: String? = null) {

        private var parsedDate: Date? = null
        val date: Date
            get() {
                val parsedDate = parsedDate
                return if (parsedDate == null) {
                    val date = ApiDateUtil.tryParseDate(this.timestamp)
                    this.parsedDate = date
                    date
                } else {
                    parsedDate
                }
            }
    }
}