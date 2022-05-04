package ru.hse.sd.rogue.game.logic.characteristics

/**
 * Health for a character.
 */
abstract class Health {
    /**
     * Maximum possible value of health.
     */
    abstract val maximum: Int

    /**
     * Current value of health.
     */
    abstract val current: Int
}

/**
 * Mutable [Health].
 */
data class MutableHealth(
    override var maximum: Int,
    override var current: Int = maximum
) : Health()