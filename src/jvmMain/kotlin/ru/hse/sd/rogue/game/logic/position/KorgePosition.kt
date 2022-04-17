package ru.hse.sd.rogue.game.logic.position

/**
 * Position to be used for KorGE API.
 */
data class KorgePosition(
    val x: Int,
    val y: Int
) {
    /**
     * Component-wise subtraction of [other] from this position.
     */
    operator fun minus(other: KorgePosition): KorgePosition = KorgePosition(x - other.x, y - other.y)

    /**
     * Component-wise addition of [other] from this position
     */
    operator fun plus(other: KorgePosition): KorgePosition = KorgePosition(x + other.x, y + other.y)

    /**
     * Negates the coordinates.
     */
    operator fun unaryMinus(): KorgePosition = KorgePosition(-x, -y)
}
