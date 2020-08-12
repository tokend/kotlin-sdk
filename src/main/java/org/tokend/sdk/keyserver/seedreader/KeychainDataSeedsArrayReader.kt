package org.tokend.sdk.keyserver.seedreader

import org.tokend.sdk.utils.statemachine.CharState
import org.tokend.sdk.utils.statemachine.CharTransition

/**
 * Reads single seed or seeds array from wallet keychain data json.
 *
 * All this is needed because strings (used in regex and json parsers) can't be erased.
 */
internal class KeychainDataSeedsArrayReader : KeychainDataSeedReader() {
    val readSeeds = mutableListOf<CharArray>()

    override val states = setOf(
            *START_TO_SEE_STATES,
            CharState.withTransitions("'seed", CharTransition('s' to "'seeds")),
            CharState.withTransitions("'seeds", CharTransition('"' to "'seeds'")),
            CharState.withTransitions(
                    "'seeds'",
                    CharTransition({ it != '[' }, "'seeds'"),
                    CharTransition('[' to "seeds_array_start")
            ),
            CharState.withTransitions(
                    "seeds_array_start",
                    CharTransition({ it != '"' }, "seeds_array_start"),
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
                    CharTransition('"' to "seeds_array_item_end") {
                        readSeeds.add(CharArray(currentSeed.size) { currentSeed[it] })
                        clearCurrentSeed()
                    }
            ),
            CharState.withTransitions(
                    "seeds_array_item_end",
                    CharTransition({ it != '"' && it != ']' }, "seeds_array_item_end"),
                    CharTransition(']' to "end"),
                    CharTransition('"' to "seed_char")
            ),
            CharState.final("end")
    )
}