package org.tokend.sdk.api.sales.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.HashCodes
import java.io.Serializable

open class SaleStatistics(@SerializedName("investors")
                          val investors: Int) : Serializable {
    override fun equals(other: Any?): Boolean {
        return other is SaleStatistics
                && other.investors == this.investors
    }

    override fun hashCode(): Int {
        return HashCodes.ofMany(investors)
    }
}