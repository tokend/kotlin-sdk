package org.tokend.sdk.api.charts.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.tokend.sdk.utils.ApiDateUtil
import java.math.BigDecimal
import java.util.*

open class AssetChartData(
    @field:JsonProperty("hour")
    val hour: List<ChartPoint>? = null,
    @field:JsonProperty("day")
    val day: List<ChartPoint>? = null,
    @field:JsonProperty("week")
    val week: List<ChartPoint>? = null,
    @field:JsonProperty("month")
    val month: List<ChartPoint>? = null,
    @field:JsonProperty("year")
    val year: List<ChartPoint>? = null
) {

    open class ChartPoint(
        @field:JsonProperty("value")
        val value: BigDecimal,
        @field:JsonProperty("timestamp")
        private val timestamp: String
    ) {

        private var parsedDate: Date? = null

        @get:JsonIgnore
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