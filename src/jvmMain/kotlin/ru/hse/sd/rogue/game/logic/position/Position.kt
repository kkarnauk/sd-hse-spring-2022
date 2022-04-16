package ru.hse.sd.rogue.game.logic.position

import ru.hse.sd.rogue.game.logic.size.cellSize
import kotlin.math.abs

/**
 * Immutable position to be used directly for the game.
 */
open class Position(
    /**
     * Number of cells before this position from the left.
     */
    open val x: Int,
    /**
     * Number of cells before this position from the top.
     */
    open val y: Int
) {
    val distanceToZero: Int
        get() = abs(x) + abs(y)

    /**
     * @return [x]
     */
    operator fun component1(): Int = x

    /**
     * @return [y]
     */
    operator fun component2(): Int = y

    /**
     * Component-wise subtraction of [other] from this position.
     */
    operator fun minus(other: Position): Position = Position(x - other.x, y - other.y)

    /**
     * Component-wise addition of [other] from this position
     */
    operator fun plus(other: Position): Position = Position(x + other.x, y + other.y)

    /**
     * Negates the coordinates.
     */
    operator fun unaryMinus(): Position = Position(-x, -y)

    operator fun rangeTo(other: Position): List<Position> {
        return sequence {
            for (i in x..other.x) {
                for (j in y..other.y) {
                    yield(Position(i, j))
                }
            }
        }.toList()
    }

    fun directionsTo(other: Position) = buildList {
        if (x < other.x) {
            add(Direction.Right)
        } else if (x > other.x) {
            add(Direction.Left)
        }

        if (y < other.y) {
            add(Direction.Down)
        } else if (y > other.y) {
            add(Direction.Up)
        }
    }

    /**
     * Copies and transforms this position into [MutablePosition].
     */
    fun asMutable(): MutablePosition = MutablePosition(x, y)

    /**
     * Converts [Position] into [KorgePosition].
     */
    fun asKorge(): KorgePosition = KorgePosition(x * cellSize, y * cellSize)

    override fun equals(other: Any?): Boolean {
        return other is Position && other.x == x && other.y == y
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

    override fun toString(): String = "Pos($x, $y)"
}

/**
 * Mutable position to be used directly for the game.
 */
class MutablePosition(
    override var x: Int,
    override var y: Int
) : Position(x, y) {
    /**
     * Copy [Position.x] and [Position.y] from [other].
     */
    fun replaceWith(other: Position) {
        x = other.x
        y = other.y
    }

    /**
     * Increase [x] by one.
     */
    fun incX(): MutablePosition = MutablePosition(x + 1, y)

    /**
     * Decrease [x] by one.
     */
    fun decX(): MutablePosition = MutablePosition(x - 1, y)

    /**
     * Increase [y] by one.
     */
    fun incY(): MutablePosition = MutablePosition(x, y + 1)

    /**
     * Decrease [y] by one.
     */
    fun decY(): MutablePosition = MutablePosition(x, y - 1)
}

/**
 * Takes this position away from the field.
 */
fun MutablePosition.takeAway() = replaceWith(Position(-1, -1))
