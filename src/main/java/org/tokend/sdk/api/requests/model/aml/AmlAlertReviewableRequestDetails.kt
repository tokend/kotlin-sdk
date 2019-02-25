package org.tokend.sdk.api.requests.model.aml

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.requests.model.base.ReviewableRequestDetails

class AmlAlertReviewableRequestDetails(
        typeI: Int,
        @SerializedName("create_aml_alert")
        val amlAlert: AmlAlertDetails

) : ReviewableRequestDetails(typeI)