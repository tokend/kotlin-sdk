package org.tokend.sdk.api.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.tokend.sdk.factory.GsonFactory

/**
 * Created by Oleg Koretsky on 11/21/17.
 */

class DataEntity<out T>(@SerializedName("data")
                        @Expose
                        val data: T) {

    fun toJson() = GsonFactory().getBaseGson().toJson(this)
}
