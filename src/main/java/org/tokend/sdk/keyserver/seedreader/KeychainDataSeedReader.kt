package org.tokend.sdk.keyserver.seedreader

import org.tokend.sdk.utils.statemachine.CharSequenceStateMachine
import org.tokend.sdk.utils.statemachine.CharState
import org.tokend.sdk.utils.statemachine.CharTransition

internal abstract class KeychainDataSeedReader : CharSequenceStateMachine() {
    override val startState = "start"

    override fun run(input: CharSequence): Boolean {
        return super.run(input).also { clearCurrentSeed() }
    }

    protected val currentSeed = ArrayList<Char>(SEED_BUFFER_SIZE)

    protected fun clearCurrentSeed() {
        currentSeed.fill('0')
        currentSeed.clear()
    }

    companion object {
        val START_TO_SEE_STATES = arrayOf(
                CharState.withTransitions("start", CharTransition('"' to "'")),
                CharState.withTransitions("'", CharTransition('s' to "'s")),
                CharState.withTransitions("'s", CharTransition('e' to "'se")),
                CharState.withTransitions("'se", CharTransition('e' to "'see")),
                CharState.withTransitions("'see", CharTransition('d' to "'seed"))
        )

        const val SEED_BUFFER_SIZE = 400
    }
}