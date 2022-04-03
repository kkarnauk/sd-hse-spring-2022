package ru.hse.sd.rogue.game.logic.position

enum class LookDirection {
    Left,
    Right
}

fun LookDirection.opposite() = when (this) {
    LookDirection.Left -> LookDirection.Right
    LookDirection.Right -> LookDirection.Left
}
