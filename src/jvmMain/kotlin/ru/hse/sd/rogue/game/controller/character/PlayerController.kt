package ru.hse.sd.rogue.game.controller.character

import ru.hse.sd.rogue.game.controller.MapController
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.position.Direction
import ru.hse.sd.rogue.game.logic.position.LookDirection
import ru.hse.sd.rogue.game.state.character.PlayerState

class PlayerController(
    actionsManager: ActionsManager,
    override val state: PlayerState,
    mapController: MapController
) : CharacterController(actionsManager, state, mapController) {
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
            if (mapController.canMoveTo(newPosition)) {
                state.position.replaceWith(newPosition)
            }
            state.lookDirection = newLookDirection
        }

    }

    fun openInventory() {
        TODO()
    }
}
