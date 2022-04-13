package ru.hse.sd.rogue.game.controller.character

import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.ai.MobStrategy
import ru.hse.sd.rogue.game.state.character.CharacterState

/**
 * Responsible for controlling mobs.
 */
class MobController(
    actionsManager: ActionsManager,
    state: CharacterState,
    movementController: MovementController,
    private val mobStrategy: MobStrategy
) : CharacterController(actionsManager, state, movementController) {
    init {
        updateRepeatable {
            mobStrategy.nextMovement()?.let { move(it) }
        }
    }
}
