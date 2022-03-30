package ru.hse.sd.rogue.game.view

import ru.hse.sd.rogue.game.logic.action.Action
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction

interface View {
    // TODO
}

class UpdateViewAction : IrreversibleAction {
    override fun invoke(): Action.Result {
        TODO("Not yet implemented")
    }
}
