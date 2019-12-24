package org.tokend.sdk.api.ingester.history.model

enum class EffectModifier {
    COMPLETED,
    PENDING,
    REVERTED;

    val i = ordinal

    companion object {
        @JvmStatic
        fun fromI(i: Int) = values().find { it.i == i }
                ?: throw IllegalArgumentException("There is no effect modifier with i=$i")
    }
}