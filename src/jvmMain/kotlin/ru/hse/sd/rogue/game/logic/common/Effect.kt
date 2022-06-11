package ru.hse.sd.rogue.game.logic.common

import java.time.Instant
import java.util.*
import ru.hse.sd.rogue.game.logic.ai.MobStrategy
import ru.hse.sd.rogue.game.logic.position.Direction

/**
 * Represents different effects of weapons of the player.
 */
sealed class Effect {
    /**
     * How exactly strategy of a mob is changes with the effect.
     */
    abstract val strategy: MobStrategy

    /**
     * Whether the effect is still in action or not.
     */
    abstract val isExpired: Boolean
}

/**
 * Represents the confusion-effect of weapons.
 * Makes a damaged mob to change its direction randomly.
 */
data class Confusion(private val expiresAt: Instant) : Effect() {
    override val strategy = object : MobStrategy() {
        private val random = Random()

        override fun nextMovement(): Direction {
            return when (random.nextInt(4)) {
                0 -> Direction.Up
                1 -> Direction.Down
                2 -> Direction.Right
                3 -> Direction.Left
                else -> throw IllegalStateException()
            }
        }
    }

    override val isExpired = Instant.now().isAfter(expiresAt)
}
