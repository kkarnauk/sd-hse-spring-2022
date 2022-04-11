package ru.hse.sd.rogue.game.controller

import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.cell.CellContent
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.MapState

/**
 * Controls one level.
 */
class MapController(
    /**
     * General actions' manager.
     */
    actionsManager: ActionsManager,
    /**
     * [MapState] of this level.
     */
    private val state: MapState
) : Controller {
    /**
     * Checks whether it's possible to move to one or another position.
     */
    fun canMoveTo(position: Position): Boolean {
        return state[position].content == CellContent.Space
    }
}
