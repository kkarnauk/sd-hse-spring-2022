package ru.hse.sd.rogue.game.state

import ru.hse.sd.rogue.game.logic.position.Position

class MapState(
    initCells: List<List<CellState>>
) : State {
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
    }

    operator fun get(x: Int, y: Int): CellState = cells[x][y]

    operator fun set(x: Int, y: Int, cell: CellState) {
        cells[x][y] = cell
    }

    operator fun get(position: Position): CellState = get(position.x, position.y)

    operator fun set(position: Position, cell: CellState) {
        set(position.x, position.y, cell)
    }

    // TODO
}
