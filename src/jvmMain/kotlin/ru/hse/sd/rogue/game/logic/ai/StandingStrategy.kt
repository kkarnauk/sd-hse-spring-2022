package ru.hse.sd.rogue.game.logic.ai

import ru.hse.sd.rogue.game.logic.position.Direction

/**
 * Represents a [MobStrategy] that has nothing to do with the player.
 */
class StandingStrategy : MobStrategy {
    override fun nextMovement(): Direction? = null
}