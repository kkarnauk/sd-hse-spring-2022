package ru.hse.sd.rogue.game.state

import ru.hse.sd.rogue.game.logic.position.Position

/**
 * State of a map.
 */
class MapState(
    /**
     * Initial state for each cell on this map.
     * The shape must be rectangle with not-zero sizes.
     */
    initCells: List<List<CellState>>
) : State, Iterable<CellState> {
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
                require(cellState.position == requirePosition) {
                    "The cell at $requirePosition stores incorrect coordinates ${cellState.position}"
                }
            }
        }
    }

    /**
     * @return [CellState] of a cell on ([x], [y]).
     */
    operator fun get(x: Int, y: Int): CellState = cells[x][y]

    /**
     * Sets [cell] for a cell on ([x], [y]).
     */
    operator fun set(x: Int, y: Int, cell: CellState) {
        require(cell.position == Position(x, y))
        cells[x][y] = cell
    }

    /**
     * @return [CellState] of a cell on [position].
     */
    operator fun get(position: Position): CellState = get(position.x, position.y)

    /**
     * Sets [cell] for a cell on [position]
     */
    operator fun set(position: Position, cell: CellState) {
        set(position.x, position.y, cell)
    }

    /**
     * @return [Iterator] of [CellState] from the top from the left.
     */
    override fun iterator(): Iterator<CellState> {
        return cells.flatten().iterator()
    }
}
