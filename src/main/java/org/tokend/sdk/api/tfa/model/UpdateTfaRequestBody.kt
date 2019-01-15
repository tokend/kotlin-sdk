package org.tokend.sdk.api.tfa.model

import com.google.gson.annotations.SerializedName

open class UpdateTfaRequestBody(@SerializedName("attributes") val attributes: TfaFactor.Attributes)