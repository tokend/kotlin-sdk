package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName

open class FavoriteEntry(
        type: String,
        key: String,
        id: Long
) {
    private class FavouriteData(
            @SerializedName("id")
            val id: String,
            @SerializedName("type")
            val type: String,
            key: String
    ) {
        @SerializedName("attributes")
        private val attributes: Map<String, String> = mapOf(
                "key" to key
        )

        val key: String
            get() = attributes["key"]!!
    }

    @SerializedName("data")
    private val data: FavouriteData = FavouriteData(id.toString(), type, key)

    val id: Long
        get() = data.id.toLong()

    val key: String
        get() = data.key

    val type: String
        get() = data.type

    override fun equals(other: Any?): Boolean {
        return other is FavoriteEntry && other.id == this.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

class SaleFavoriteEntry @JvmOverloads constructor(asset: String,
                                                  id: Long = 0L
) : FavoriteEntry(TYPE, asset, id) {
    companion object {
        const val TYPE = "sale"
    }
}

class AssetPairFavoriteEntry @JvmOverloads constructor(base: String,
                                                       quote: String,
                                                       id: Long = 0L
) : FavoriteEntry(TYPE, "$base-$quote", id) {
    companion object {
        const val TYPE = "asset_pair"
    }
}