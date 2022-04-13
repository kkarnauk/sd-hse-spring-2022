package ru.hse.sd.rogue.game.logic.ai

import ru.hse.sd.rogue.game.logic.position.Direction

interface MobStrategy {
    fun nextMovement(): Direction?
}