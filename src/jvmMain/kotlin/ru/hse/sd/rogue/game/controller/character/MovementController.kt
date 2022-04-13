package ru.hse.sd.rogue.game.controller.character

import ru.hse.sd.rogue.game.controller.Controller
import ru.hse.sd.rogue.game.controller.MapController
import ru.hse.sd.rogue.game.logic.position.Direction
import ru.hse.sd.rogue.game.logic.position.Position

/**
 * Responsible for tracking whether it's permitted to move to one or another position.
 */
class MovementController(private val mapController: MapController) : Controller {
    fun canMoveTo(newPosition: Position): Boolean = mapController.canMoveTo(newPosition)
    fun canMoveFrom(position: Position) = listOf<Pair<Direction, Position>>(
        Direction.Up to position.asMutable().decY(),
        Direction.Down to position.asMutable().incY(),
        Direction.Left to position.asMutable().decX(),
        Direction.Right to position.asMutable().incX(),
    ).filter {
        canMoveTo(it.second)
    }.map { it.first }
}
