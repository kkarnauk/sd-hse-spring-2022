package ru.hse.sd.rogue.game.logic.characteristics

/**
 * Durability of an item.
 */
abstract class Durability {
    /**
     * Max value of durability.
     */
    abstract val maximum: Int

    /**
     * Current value of durability.
     */
    abstract val current: Int
}

/**
 * Mutable [Durability].
 */
data class MutableDurability(
    override var maximum: Int,
    override var current: Int = maximum
) : Durability()
