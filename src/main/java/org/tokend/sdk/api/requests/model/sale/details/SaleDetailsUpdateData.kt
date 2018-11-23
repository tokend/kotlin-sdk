package org.tokend.sdk.api.requests.model.sale.details

import com.google.gson.annotations.SerializedName

class SaleDetailsUpdateData(
        @SerializedName("sale_id")
        val saleId: Long,
        @SerializedName("new_details")
        val newDetails: Details
) {
    class Details(
            @SerializedName("description")
            val description: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("short_description")
            val shortDescription: String,
            @SerializedName("logo")
            val logo: Logo
    ) {
        class Logo(
                @SerializedName("key")
                val key: String,
                @SerializedName("type")
                val type: String = "fund_logo"
        )
    }
}