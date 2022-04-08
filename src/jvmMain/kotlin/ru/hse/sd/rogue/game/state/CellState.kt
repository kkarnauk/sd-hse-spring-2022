package ru.hse.sd.rogue.game.state

import ru.hse.sd.rogue.game.logic.cell.CellContent
import ru.hse.sd.rogue.game.logic.position.Position

/**
 * State of a cell on a map.
 */
class CellState(
    /**
     * [Position] of this cell.
     */
    val position: Position,
    /**
     * [CellContent] of this cell.
     */
    val content: CellContent
) : State
