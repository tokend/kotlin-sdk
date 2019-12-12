package org.tokend.sdk.api.ingester.assets.model

enum class AssetState(val value: Int) {
    ACTIVE(0),
    DELETED(1);

    companion object {
        @JvmStatic
        fun fromValue(value: Int): AssetState {
            return when (value) {
                ACTIVE.value -> ACTIVE
                DELETED.value -> DELETED
                else -> throw IllegalArgumentException("There is no asset state with value '$value'")
            }
        }

        @JvmStatic
        fun fromName(name: String): AssetState {
            return when (name.toUpperCase()) {
                ACTIVE.name -> ACTIVE
                DELETED.name -> DELETED
                else -> throw IllegalArgumentException("There is no asset state with name '$name'")
            }
        }
    }
}