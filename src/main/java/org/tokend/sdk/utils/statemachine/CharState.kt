package org.tokend.sdk.utils.statemachine

internal abstract class CharState(open val name: String,
                         open val isFinal: Boolean) {
    abstract fun doTransition(char: Char): String?

    override fun equals(other: Any?): Boolean {
        return other is CharState && other.name == this.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun toString(): String {
        return "CharState($name){final=$isFinal}@${hashCode()}"
    }

    companion object {
        fun withTransitions(name: String,
                            vararg transitions: CharTransition): CharState {
            return object : CharState(
                    name = name,
                    isFinal = transitions.isEmpty()
            ) {
                override fun doTransition(char: Char): String? {
                    return transitions
                            .find { it.predicate(char) }
                            ?.run {
                                callback(char)
                                newState
                            }
                }
            }
        }

        fun final(name: String): CharState {
            return object : CharState(
                    name = name,
                    isFinal = true
            ) {
                override fun doTransition(char: Char): String? = null
            }
        }
    }
}