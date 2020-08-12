package org.tokend.sdk.test

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.utils.statemachine.CharSequenceStateMachine
import org.tokend.sdk.utils.statemachine.CharState
import org.tokend.sdk.utils.statemachine.CharTransition

class CharSequenceStateMachineTest {
    private val catsMachine = object : CharSequenceStateMachine() {
        override val states = setOf(
                CharState.withTransitions(
                        "start",
                        CharTransition('c' to "c")
                ),
                CharState.withTransitions(
                        "c",
                        CharTransition('a' to "ca")
                ),
                CharState.withTransitions(
                        "ca",
                        CharTransition('t' to "cat")
                ),
                CharState.withTransitions(
                        "cat",
                        CharTransition('s' to "cats")
                ),
                CharState.final("cats")
        )

        override val startState = "start"
    }

    @Test
    fun reachFinalState() {
        val input = "We have a categorized list of cats and dogs"
        Assert.assertTrue(catsMachine.run(input))
    }

    @Test
    fun didNotReachFinalState() {
        val input = "catScaOcCATS"
        Assert.assertFalse(catsMachine.run(input))
    }
}