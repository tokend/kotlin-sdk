package org.tokend.sdk.api.ingester.history.model

enum class EffectType {
    // balance received funds from other balance
    FUNDED,
    // funds have been issued to the balance
    ISSUED,
    // balance has been charged
    CHARGED,
    //  balance has been charged from locked and corresponding amount of tokens has been destroyed
    DESTROYED,
    // funds has been locked on the balance
    LOCKED,
    // funds has been unlocked on the balance
    UNLOCKED,
    // funds has been charged from locked amount on balance
    CHARGED_FROM_LOCKED,
    // account entry roles have changed
    ROLES_CHANGED;

    val i: Int = ordinal + 1

    companion object {
        @JvmStatic
        fun fromI(i: Int) = values().find { it.i == i }
                ?: throw IllegalArgumentException("There is no effect type with i=$i")
    }
}