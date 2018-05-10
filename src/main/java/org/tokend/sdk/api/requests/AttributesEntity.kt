package org.tokend.sdk.api.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AttributesEntity<out T>(@SerializedName("attributes")
                                    @Expose
                                    val attributes: T)