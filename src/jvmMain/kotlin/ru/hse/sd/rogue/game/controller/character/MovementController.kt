package ru.hse.sd.rogue.game.controller.character

import ru.hse.sd.rogue.game.controller.Controller
import ru.hse.sd.rogue.game.controller.MapController
import ru.hse.sd.rogue.game.logic.action.*
import ru.hse.sd.rogue.game.logic.position.Direction
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.character.MovementState

/**
 * Responsible for tracking whether it's permitted to move to one or another position.
 * Also, controls maximum possible speed for a character.
 */
class MovementController(
    private val actionsManager: ActionsManager,
    private val state: MovementState,
    private val mapController: MapController
) : Controller {
    /**
     * Tells whether it's possible or not to move to [newPosition].
     */
    fun canMoveTo(newPosition: Position): Boolean {
        return state.canMove && mapController.canMoveTo(newPosition)
    }

    /**
     * @return all possible [Direction] that one can go from [position].
     */
    fun canMoveFrom(position: Position): List<Direction> {
        if (!state.canMove) return emptyList()

        return listOf(
            Direction.Up to position.asMutable().decY(),
            Direction.Down to position.asMutable().incY(),
            Direction.Left to position.asMutable().decX(),
            Direction.Right to position.asMutable().incX(),
        ).filter { canMoveTo(it.second) }.map { it.first }
    }

    /**
     * Invoked when a character makes a move.
     * Must be invoked from an action.
     */
    fun moveCompleted() {
        check(state.canMove) { "Moved when prohibited." }
        state.canMove = false
        actionsManager.register(ActionPriority.Low, object : ReversibleAction {
            override fun invoke() = Unit

            override val lifetime: Lifetime
                get() = Lifetime(state.speed.ticksToReload.toLong())

            override fun reverse(): Action = IrreversibleAction {
                state.canMove = true
            }
        })
    }
}
