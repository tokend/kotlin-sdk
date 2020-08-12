package org.tokend.sdk.utils.statemachine

internal abstract class CharSequenceStateMachine {
    protected abstract val states: Set<CharState>
    protected abstract val startState: String

    /**
     * @return true if the machine has reached the final state
     */
    open fun run(input: CharSequence): Boolean {
        val statesMap = states.associateBy(CharState::name)
        val startState = statesMap.getValue(startState)
        var state = startState

        input.forEach { char ->
            state = state.doTransition(char)?.let { newState ->
                statesMap[newState]
                        ?: throw IllegalStateException("State '$newState' not found")
            } ?: startState

            if (state.isFinal) {
                return true
            }
        }

        return false
    }
}