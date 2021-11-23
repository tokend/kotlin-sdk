package org.tokend.sdk.api.identity.model

import com.fasterxml.jackson.annotation.JsonProperty


class MassEmailAccountKey(
    @JsonProperty("id")
    val accountId: String
) {
    @JsonProperty("type")
    val type = "accounts"
}