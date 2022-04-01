package ru.hse.sd.rogue.game.controller.character

import ru.hse.sd.rogue.game.controller.MapController
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.position.Direction
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
        if (mapController.canMoveTo(newPosition)) {
            update {
                state.position.replaceWith(newPosition)
            }
        }
    }

    fun openInventory() {
        TODO()
    }
}
