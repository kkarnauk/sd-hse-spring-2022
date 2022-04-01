package ru.hse.sd.rogue.game.state

import ru.hse.sd.rogue.game.logic.cell.CellContent
import ru.hse.sd.rogue.game.logic.position.Position

class MapState(
    initCells: List<List<CellState>>
) : State, Sequence<CellState> {
    private val cells: MutableList<MutableList<CellState>> = initCells.map { it.toMutableList() }.toMutableList()

    init {
        require(cells.isNotEmpty()) {
            "Cannot construct an empty map."
        }
        cells.forEach { cell ->
            require(cell.size == cells.first().size) {
                "Map must be a rectangle."
            }
        }
        cells.forEachIndexed { x, yLine ->
            yLine.forEachIndexed { y, cellState ->
                val requirePosition = Position(x, y)
                require(cellState.position == Position(x, y)) {
                    "The cell at $requirePosition stores incorrect coordinates ${cellState.position}"
                }
            }
        }
    }

    operator fun get(x: Int, y: Int): CellState = cells[x][y]

    operator fun set(x: Int, y: Int, cell: CellState) {
        require(cell.position == Position(x, y))
        cells[x][y] = cell
    }

    operator fun get(position: Position): CellState = get(position.x, position.y)

    operator fun set(position: Position, cell: CellState) {
        set(position.x, position.y, cell)
    }

    override fun iterator(): Iterator<CellState> {
        return cells.flatten().iterator()
    }
}
