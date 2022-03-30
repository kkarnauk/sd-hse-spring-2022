package ru.hse.sd.rogue.game.state

import ru.hse.sd.rogue.game.logic.cell.CellContent
import ru.hse.sd.rogue.game.logic.position.Position

class CellState(
    val position: Position,
    val content: CellContent
) : State
