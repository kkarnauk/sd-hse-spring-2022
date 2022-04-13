package ru.hse.sd.rogue.game.logic.ai

import ru.hse.sd.rogue.game.controller.character.MovementController
import ru.hse.sd.rogue.game.logic.position.Direction
import ru.hse.sd.rogue.game.state.character.PlayerState
import ru.hse.sd.rogue.game.state.character.mob.MobState

class CowardlyStrategy(
    private val playerState: PlayerState,
    private val mobState: MobState,
    private val movementController: MovementController,
    private val safeDistance: Int,
) : MobStrategy {
    override fun nextMovement(): Direction? {
        val difference = playerState.position - mobState.position
        if (difference.length >= safeDistance) {
            return null
        }
        val directionsFromPlayer = playerState.position.directionsTo(mobState.position)
        val directions = movementController.canMoveFrom(mobState.position)
            .filter { it in directionsFromPlayer }
        return directions.randomOrNull()
    }

}