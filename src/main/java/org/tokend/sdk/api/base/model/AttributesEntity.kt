package org.tokend.sdk.api.base.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class AttributesEntity<out T>(@SerializedName("attributes")
                                    @Expose
                                    val attributes: T)