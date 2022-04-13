package ru.hse.sd.rogue.game.controller.character

import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.state.character.PlayerState

/**
 * Responsible for controlling the player.
 */
class PlayerController(
    actionsManager: ActionsManager,
    override val state: PlayerState,
    movementController: MovementController
) : CharacterController(actionsManager, state, movementController) {

    fun openInventory() {
        TODO()
    }
}
