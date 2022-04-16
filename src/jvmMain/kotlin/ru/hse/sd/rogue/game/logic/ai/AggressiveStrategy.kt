package ru.hse.sd.rogue.game.logic.ai

import ru.hse.sd.rogue.game.controller.character.MovementController
import ru.hse.sd.rogue.game.logic.position.Direction
import ru.hse.sd.rogue.game.state.character.PlayerState
import ru.hse.sd.rogue.game.state.character.mob.MobState

class AggressiveStrategy(
    private val playerState: PlayerState,
    private val mobState: MobState,
    private val movementController: MovementController,
    private val sensitiveDistance: Int,
) : MobStrategy {
    override fun nextMovement(): Direction? {
        val difference = playerState.position - mobState.position
        if (difference.distanceToZero > sensitiveDistance) {
            return null
        }
        val directionsToPlayer = mobState.position.directionsTo(playerState.position)
        val directions = movementController.canMoveFrom(mobState.position)
            .filter { it in directionsToPlayer }
        return directions.randomOrNull()
    }

}