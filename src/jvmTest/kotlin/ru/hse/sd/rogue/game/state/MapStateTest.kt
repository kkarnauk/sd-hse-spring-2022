package ru.hse.sd.rogue.game.state

import org.junit.Test
import ru.hse.sd.rogue.game.logic.cell.CellContent
import ru.hse.sd.rogue.game.logic.position.Position
import kotlin.test.assertEquals
import kotlin.test.assertFails


internal class MapStateTest {

    @Test
    fun failCreateFromWrongCells() {
        assertFails {
            MapState(listOf(listOf(CellState(Position(1, 0), CellContent.Space))))
        }
        assertFails {
            MapState(
                listOf(
                    listOf(
                        CellState(Position(0, 0), CellContent.Space),
                        CellState(Position(0, 1), CellContent.Space)
                    ),
                    listOf(
                        CellState(Position(1, 0), CellContent.Space)
                    )
                )
            )
        }
        assertFails {
            MapState(
                listOf(
                    listOf(
                        CellState(Position(0, 0), CellContent.Space),
                        CellState(Position(1, 0), CellContent.Space)
                    )
                )
            )
        }
    }

    @Test
    fun get(): Unit = with(smallMap()) {
        assertEquals(CellState(Position(0, 0), CellContent.Space), get(Position(0, 0)))
        assertEquals(CellState(Position(0, 1), CellContent.Wall), get(Position(0, 1)))
        assertFails {
            get(Position(-1, -1))
        }
    }


    @Test
    fun set(): Unit = with(smallMap()) {
        set(Position(0, 0), CellState(Position(0, 0), CellContent.Wall))
        assertEquals(CellState(Position(0, 0), CellContent.Wall), get(Position(0, 0)))
    }

    private fun smallMap() = MapState(
        listOf(
            listOf(
                CellState(Position(0, 0), CellContent.Space),
                CellState(Position(0, 1), CellContent.Wall)
            )
        )
    )
}