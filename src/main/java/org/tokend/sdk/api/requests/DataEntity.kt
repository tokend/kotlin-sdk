package org.tokend.sdk.api.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.base.factory.GsonFactory

open class DataEntity<out T>(@SerializedName("data")
                        @Expose
                        val data: T) {

    fun toJson() = GsonFactory().getBaseGson().toJson(this)
}
