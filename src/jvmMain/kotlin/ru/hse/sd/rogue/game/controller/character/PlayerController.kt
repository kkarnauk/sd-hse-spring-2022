package ru.hse.sd.rogue.game.controller.character

import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.position.Direction
import ru.hse.sd.rogue.game.logic.position.LookDirection
import ru.hse.sd.rogue.game.state.character.PlayerState

/**
 * Responsible for controlling the player.
 */
class PlayerController(
    actionsManager: ActionsManager,
    override val state: PlayerState,
    movementController: MovementController
) : CharacterController(actionsManager, state, movementController) {
    override fun move(direction: Direction) {
        val newPosition = when (direction) {
            Direction.Up -> state.position.decY()
            Direction.Down -> state.position.incY()
            Direction.Left -> state.position.decX()
            Direction.Right -> state.position.incX()
        }
        val newLookDirection = when (direction) {
            Direction.Left -> LookDirection.Left
            Direction.Right -> LookDirection.Right
            else -> state.lookDirection
        }
        update {
            if (movementController.canMoveTo(newPosition)) {
                state.position.replaceWith(newPosition)
            }
            state.lookDirection = newLookDirection
        }

    }

    fun openInventory() {
        TODO()
    }
}
