package org.tokend.sdk.api.requests.model.sale.endtime

import com.google.gson.annotations.SerializedName
import java.util.*

class SaleEndTimeUpdateDetails(
        @SerializedName("sale_id")
        val saleId: Long,
        @SerializedName("new_end_time")
        val newEndTime: Date
)