package ru.hse.sd.rogue.game.controller

import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.registerRepeatable
import ru.hse.sd.rogue.game.state.character.MobState
import ru.hse.sd.rogue.game.state.character.PlayerState

/**
 * Controls the whole game and checks whether it is finished or not.
 */
class GlobalController(
    /**
     * Actions manager to manage controlling of game.
     */
    actionsManager: ActionsManager,
    /**
     * State of the player.
     */
    private val playerState: PlayerState,
    /**
     * State of the boss.
     */
    private val bossState: MobState,
    /**
     * What should happen when the player or the boss dies.
     */
    private val onEnd: () -> Unit
) : Controller {
    init {
        actionsManager.registerRepeatable {
            if (!playerState.isAlive || !bossState.isAlive) {
                onEnd()
            }
        }
    }
}
