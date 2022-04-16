package ru.hse.sd.rogue.game.logic.ai

import ru.hse.sd.rogue.game.logic.position.Direction

/**
 * Represents a strategy for a mob that handles its movement.
 */
interface MobStrategy {
    /**
     * Determines the next movement of a mob.
     */
    fun nextMovement(): Direction?
}