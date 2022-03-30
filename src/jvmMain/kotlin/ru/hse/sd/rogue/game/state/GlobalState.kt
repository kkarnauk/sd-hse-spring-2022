package ru.hse.sd.rogue.game.state

import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction

class GlobalState(
    private val actionsManager: ActionsManager
) : State {
    init {
        registerCheckGame()
    }

    private fun registerCheckGame() {
        actionsManager.register(IrreversibleAction {
            checkGame()
            registerCheckGame()
        })
    }

    private fun checkGame() {
        println("Hello world")
    }
}
