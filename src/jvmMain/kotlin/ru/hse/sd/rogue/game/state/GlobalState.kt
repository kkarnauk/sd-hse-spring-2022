package ru.hse.sd.rogue.game.state

import ru.hse.sd.rogue.game.logic.action.ActionPriority
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.state.character.Player
import ru.hse.sd.rogue.game.state.character.mob.BossMob

class GlobalState(
    private val actionsManager: ActionsManager,
    private val player: Player,
    private val boss: BossMob
) : State {
    init {
        registerCheckGame()
    }

    private fun registerCheckGame() {
        actionsManager.register(ActionPriority.High, IrreversibleAction {
            checkGame()
            registerCheckGame()
        })
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
