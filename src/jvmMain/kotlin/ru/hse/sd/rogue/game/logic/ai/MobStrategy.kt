package ru.hse.sd.rogue.game.logic.ai

import ru.hse.sd.rogue.game.logic.common.Effect
import ru.hse.sd.rogue.game.logic.position.Direction

/**
 * Represents a strategy for a mob that handles its movement.
 */
abstract class MobStrategy {
    /**
     * Current [Effect]
     */
    var effect: Effect? = null

    /**
     * Determines the next movement of a mob.
     */
    abstract fun nextMovement(): Direction?
}

fun MobStrategy.withExpirableEffects() = object : MobStrategy() {
    override fun nextMovement(): Direction? {
        if (this@withExpirableEffects.effect?.isExpired == true) {
            effect = null
        }
        return this@withExpirableEffects.nextMovement()
    }
}
