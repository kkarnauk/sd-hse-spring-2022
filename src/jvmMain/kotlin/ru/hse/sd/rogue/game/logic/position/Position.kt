package ru.hse.sd.rogue.game.logic.position

data class Position(
    var x: Int,
    var y: Int
) {
    fun replaceWith(other: Position) {
        x = other.x
        y = other.y
    }

    fun incX(): Position = Position(x + 1, y)

    fun decX(): Position = Position(x - 1, y)

    fun incY(): Position = Position(x, y + 1)

    fun decY(): Position = Position(x, y - 1)
}
