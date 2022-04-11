package ru.hse.sd.rogue.game.logic.characteristics

/**
 * Durability of an item.
 */
data class Durability(
    /**
     * Max value of durability.
     */
    var maximum: Int,
    /**
     * Current value of durability.
     */
    var current: Int = maximum
)
