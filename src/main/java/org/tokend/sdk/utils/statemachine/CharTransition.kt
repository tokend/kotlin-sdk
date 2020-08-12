package org.tokend.sdk.utils.statemachine

internal class CharTransition(
        val predicate: (Char) -> Boolean,
        val newState: String,
        val callback: (Char) -> Unit = {}
) {
    constructor(
            char: Char,
            newState: String,
            callback: (Char) -> Unit = {}
    ) : this(
            predicate = { it == char },
            newState = newState,
            callback = callback
    )

    constructor(
            transition: Pair<Char, String>,
            callback: (Char) -> Unit = {}
    ) : this(
            char = transition.first,
            newState = transition.second,
            callback = callback
    )
}