package ru.hse.sd.rogue.game.logic.characteristics

/**
 * Health for a character.
 */
data class Health(
    /**
     * Maximum possible value of health.
     */
    var maximum: Int,
    /**
     * Current value of health.
     */
    var current: Int = maximum
)
