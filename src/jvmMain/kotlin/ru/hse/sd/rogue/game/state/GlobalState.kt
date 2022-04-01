package ru.hse.sd.rogue.game.state

import ru.hse.sd.rogue.game.logic.action.ActionPriority
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.registerRepeatable
import ru.hse.sd.rogue.game.state.character.Player
import ru.hse.sd.rogue.game.state.character.mob.BossMob

class GlobalState(
    private val actionsManager: ActionsManager,
    private val player: Player,
    private val boss: BossMob
) : State {
    init {
        actionsManager.registerRepeatable(ActionPriority.High) {
            checkGame()
        }
    }

    private fun checkGame() {
        if (!player.isAlive) {
            loseGame()
        } else if (!boss.isAlive) {
            winGame()
        }
    }

    private fun winGame() {
        TODO()
    }

    private fun loseGame() {
        TODO()
    }
}
