package ru.hse.sd.rogue.game.logic.characteristics

/**
 * Responsible for calculating a damage depending on [minimum] and [maximum].
 */
abstract class Damage {
    /**
     * Minimum possible damage.
     */
    abstract val minimum: Int

    /**
     * Maximum possible damage.
     */
    abstract val maximum: Int

    /**
     * Current damage, uniformly distributed between [minimum] and [maximum].
     */
    val value: Int
        get() = (minimum..maximum).random()

    /**
     * Average damage
     */
    val average: Double
        get() = (minimum + maximum).toDouble() / 2

    /**
     * Constructs [MutableDamage] from this one.
     */
    fun asMutable(): MutableDamage = MutableDamage(minimum, maximum)
}


/**
 * Mutable [Damage].
 */
data class MutableDamage(
    override var minimum: Int,
    override var maximum: Int
) : Damage()
