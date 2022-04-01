package ru.hse.sd.rogue.game.view.character

import com.soywiz.korge.view.Container
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.action.registerRepeatable
import ru.hse.sd.rogue.game.state.character.CharacterState
import ru.hse.sd.rogue.game.view.View

abstract class CharacterView(
    actionsManager: ActionsManager,
    protected val container: Container,
    protected val characterState: CharacterState
) : View, IrreversibleAction {
}