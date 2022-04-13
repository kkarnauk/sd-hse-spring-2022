package ru.hse.sd.rogue.game.logic.ai

import ru.hse.sd.rogue.game.logic.position.Direction

class StandingStrategy : MobStrategy {
    override fun nextMovement(): Direction? = null
}