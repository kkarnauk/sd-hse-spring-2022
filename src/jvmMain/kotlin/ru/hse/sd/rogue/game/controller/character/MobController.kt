package ru.hse.sd.rogue.game.controller.character

import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.position.Direction
import ru.hse.sd.rogue.game.state.character.CharacterState

/**
 * Responsible for controlling mobs.
 */
class MobController(
    actionsManager: ActionsManager,
    state: CharacterState,
    movementController: MovementController
) : CharacterController(actionsManager, state, movementController) {
    // TODO

    override fun move(direction: Direction) {
        TODO("Not yet implemented")
    }
}
