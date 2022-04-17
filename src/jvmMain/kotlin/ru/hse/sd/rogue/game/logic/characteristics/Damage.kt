package ru.hse.sd.rogue.game.logic.characteristics

/**
 * Responsible for calculating a damage depending on [minimum] and [maximum].
 */
data class Damage(
    /**
     * Minimum possible damage.
     */
    var minimum: Int,
    /**
     * Maximum possible damage.
     */
    var maximum: Int
) {
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
}
