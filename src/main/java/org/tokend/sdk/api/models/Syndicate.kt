package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName
import java.util.*

open class Syndicate(
        @SerializedName("name") val name: String? = null,
        @SerializedName("homepage") val homepage: String? = null,
        @SerializedName("headquarters") val location: String? = null,
        @SerializedName("industry") val industry: String? = null,
        @SerializedName("founded") val foundedDate: Date? = null,
        @SerializedName("teamSize") val teamSize: Int? = null,
        @SerializedName("social") val social: SocialLinks? = null,
        @SerializedName("members") val members: List<Member>? = null) {

    class Member(@SerializedName("name") val name: String? = null,
                 @SerializedName("isLeader") val isLeader: Boolean? = null,
                 @SerializedName("image") val image: RemoteFile? = null,
                 @SerializedName("social") val social: SocialLinks? = null)
}