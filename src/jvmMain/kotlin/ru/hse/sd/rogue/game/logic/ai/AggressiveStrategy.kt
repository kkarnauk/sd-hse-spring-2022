package ru.hse.sd.rogue.game.logic.ai

import ru.hse.sd.rogue.game.controller.character.MovementController
import ru.hse.sd.rogue.game.logic.position.Direction
import ru.hse.sd.rogue.game.state.character.PlayerState
import ru.hse.sd.rogue.game.state.character.mob.MobState

/**
 * Represents [MobStrategy] that is trying to follow the player if he's within [sensitiveDistance].
 */
class AggressiveStrategy(
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
    private val sensitiveDistance: Int
) : MobStrategy() {
    override fun nextMovement(): Direction? {
        return when (val strategy = effect?.strategy) {
            null -> {
                val difference = playerState.position - mobState.position
                if (difference.distanceToZero > sensitiveDistance) {
                    return null
                }
                val directionsToPlayer = mobState.position.directionsTo(playerState.position)
                val directions = movementController.canMoveFrom(mobState.position)
                    .filter { it in directionsToPlayer }
                directions.randomOrNull()
            }
            else -> strategy.nextMovement()
        }
    }

}