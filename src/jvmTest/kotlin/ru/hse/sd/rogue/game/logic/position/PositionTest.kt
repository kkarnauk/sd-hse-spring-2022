package ru.hse.sd.rogue.game.logic.position

import org.junit.Test
import ru.hse.sd.rogue.game.logic.size.cellSize
import kotlin.test.assertEquals


internal class PositionTest {

    @Test
    fun minus() {
        assertEquals(Position(5, -10), Position(10, 20) - Position(5, 30))
    }

    @Test
    operator fun unaryMinus() {
        assertEquals(Position(-5, 10), -Position(5, -10))
    }

    @Test
    fun asMutable() {
        val position = Position(5, -10)
        position.asMutable().decX()
        assertEquals(Position(5, -10), position)
    }

    @Test
    fun asKorge() {
        assertEquals(KorgePosition(-5 * cellSize, 10 * cellSize), Position(-5, 10).asKorge())
    }

    @Test
    fun getX() {
        val position = Position(5, -10)
        assertEquals(5, position.x)
    }

    @Test
    fun getY() {
        val position = Position(5, -10)
        assertEquals(-10, position.y)
    }
}