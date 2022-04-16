package ru.hse.sd.rogue.game.logic.ai

import ru.hse.sd.rogue.game.controller.character.MovementController
import ru.hse.sd.rogue.game.logic.position.Direction
import ru.hse.sd.rogue.game.state.character.PlayerState
import ru.hse.sd.rogue.game.state.character.mob.MobState

/**
 * Represents a [MobStrategy] that is trying to go away from the player.
 */
class CowardlyStrategy(
    /**
     * State of the player.
     */
    private val playerState: PlayerState,
    /**
     * State of the mob to follow the player.
     */
    private val mobState: MobState,
    /**
     * Controller of all movements in the game.
     */
    private val movementController: MovementController,
    /**
     * Determines how close the mob should be to start following.
     */
    private val safeDistance: Int,
) : MobStrategy {
    override fun nextMovement(): Direction? {
        val difference = playerState.position - mobState.position
        if (difference.distanceToZero >= safeDistance) {
            return null
        }
        val directionsFromPlayer = playerState.position.directionsTo(mobState.position)
        val directions = movementController.canMoveFrom(mobState.position)
            .filter { it in directionsFromPlayer }
        return directions.randomOrNull()
    }

}