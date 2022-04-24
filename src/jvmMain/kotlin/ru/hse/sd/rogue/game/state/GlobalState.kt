package ru.hse.sd.rogue.game.state

import ru.hse.sd.rogue.game.state.character.PlayerState
import ru.hse.sd.rogue.game.state.character.mob.BigDemonMobState

/**
 * Global state of the game.
 */
class GlobalState(
    /**
     * State of the player.
     */
    private val player: PlayerState,
    /**
     * State of the boss.
     */
    private val boss: BigDemonMobState
) : State
