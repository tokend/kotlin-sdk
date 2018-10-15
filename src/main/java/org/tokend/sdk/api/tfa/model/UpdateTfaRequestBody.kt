package org.tokend.sdk.api.tfa.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.tfa.model.TfaFactor

open class UpdateTfaRequestBody(@SerializedName("attributes") val attributes: TfaFactor.Attributes)