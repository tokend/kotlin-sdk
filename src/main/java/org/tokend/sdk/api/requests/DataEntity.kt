package org.tokend.sdk.api.requests

import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Oleg Koretsky on 11/21/17.
 */

class DataEntity<out T>(@SerializedName("data")
                        @Expose
                        val data: T) {

    fun toJson() = Gson().toJson(this)
}
