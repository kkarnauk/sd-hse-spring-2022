package ru.hse.sd.rogue.game.logic.characteristics

/**
 * Speed of a character.
 */
data class Speed(
    /**
     * Number of ticks after which it's again possible to use a weapon of make a move.
     */
    var ticksToReload: Int
)
