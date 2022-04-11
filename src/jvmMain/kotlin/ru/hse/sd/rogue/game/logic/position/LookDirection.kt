package ru.hse.sd.rogue.game.logic.position

/**
 * Determines where a character can look.
 */
enum class LookDirection {
    Left,
    Right
}

/**
 * Mirror [LookDirection].
 */
fun LookDirection.opposite() = when (this) {
    LookDirection.Left -> LookDirection.Right
    LookDirection.Right -> LookDirection.Left
}
