package ru.hse.sd.rogue.game.controller

import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.position.Direction

abstract class CharacterController(
    protected val actionsManager: ActionsManager
) : Controller {
    fun action(act: () -> Unit) {
        actionsManager.register(CharacterAction(act))
    }

    abstract fun move(direction: Direction)
}

class CharacterAction(private val action: () -> Unit) : IrreversibleAction {
    override fun invoke() = action()
}
