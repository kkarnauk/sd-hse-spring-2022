package ru.hse.sd.rogue.game.controller

import ru.hse.sd.rogue.game.logic.action.Action
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction

interface CharacterController : Controller {
    // TODO()
}

class CharacterAction : IrreversibleAction {
    override fun invoke(): Action.Result {
        TODO("Not yet implemented")
    }
}
