package ru.hse.sd.rogue.game.logic.position

open class Position(
    open val x: Int,
    open val y: Int
) {
    operator fun component1(): Int = x

    operator fun component2(): Int = y

    override fun equals(other: Any?): Boolean {
        return other is Position && other.x == x && other.y == y
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}

class MutablePosition(
    override var x: Int,
    override var y: Int
) : Position(x, y) {
    fun replaceWith(other: MutablePosition) {
        x = other.x
        y = other.y
    }

    fun incX(): MutablePosition = MutablePosition(x + 1, y)

    fun decX(): MutablePosition = MutablePosition(x - 1, y)

    fun incY(): MutablePosition = MutablePosition(x, y + 1)

    fun decY(): MutablePosition = MutablePosition(x, y - 1)
}
