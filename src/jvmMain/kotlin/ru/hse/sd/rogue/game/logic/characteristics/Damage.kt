package ru.hse.sd.rogue.game.logic.characteristics

/**
 * Responsible for calculating a damage depending on [minimum] and [maximum].
 */
data class Damage(
    /**
     * Minimum possible damage.
     */
    val minimum: Int,
    /**
     * Maximum possible damage.
     */
    val maximum: Int
) {
    /**
     * Current damage, uniformly distributed between [minimum] and [maximum].
     */
    val value: Int
        get() = (minimum..maximum).random()
}
