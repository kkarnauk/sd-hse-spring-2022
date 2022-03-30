package ru.hse.sd.rogue.game.controller

import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction

abstract class CharacterController(
    protected val actionsManager: ActionsManager
) : Controller {
    fun action(act: () -> Unit) {
        actionsManager.register(CharacterAction(act))
    }
}

class CharacterAction(private val action: () -> Unit) : IrreversibleAction {
    override fun invoke() = action()
}
