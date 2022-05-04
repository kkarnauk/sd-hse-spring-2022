package ru.hse.sd.rogue.game.logic.characteristics

/**
 * Speed of a character.
 */
abstract class Speed {
    /**
     * Number of ticks after which it's again possible to use a weapon of make a move.
     */
    abstract val ticksToReload: Int
}

/**
 * Mutable [Speed].
 */
data class MutableSpeed(
    override var ticksToReload: Int
) : Speed()
