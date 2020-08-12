package org.tokend.sdk.keyserver.seedreader

import org.tokend.sdk.utils.statemachine.CharState
import org.tokend.sdk.utils.statemachine.CharTransition

internal class KeychainDataSingleSeedReader : KeychainDataSeedReader() {
    var readSeed: CharArray? = null

    override val states = setOf(
            *START_TO_SEE_STATES,
            CharState.withTransitions("'seed", CharTransition('"' to "'seed'")),
            CharState.withTransitions(
                    "'seed'",
                    CharTransition({ it != '"' }, "'seed'"),
                    CharTransition('"' to "seed_char")
            ),
            CharState.withTransitions(
                    "seed_char",
                    CharTransition(
                            { it != '"' },
                            "seed_char",
                            {
                                currentSeed.add(it)

                                // Prevent array copy on ensuring capacity
                                if (currentSeed.size >= SEED_BUFFER_SIZE / 4) {
                                    clearCurrentSeed()
                                }
                            }
                    ),
                    CharTransition('"' to "end") {
                        readSeed = CharArray(currentSeed.size) { currentSeed[it] }
                    }
            ),
            CharState.final("end")
    )
}