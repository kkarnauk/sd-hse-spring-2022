package ru.hse.sd.rogue.game.logic.ai

import ru.hse.sd.rogue.game.controller.CollisionsController
import ru.hse.sd.rogue.game.controller.character.MobController
import ru.hse.sd.rogue.game.controller.character.MovementController
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.level.Level
import ru.hse.sd.rogue.game.logic.position.Direction
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.CloneableState
import ru.hse.sd.rogue.game.state.character.MobState

class ReproductiveStrategy<T>(
    mobState: T,
    frequency: Long,
    probability: Double,
    gameLevel: Level,
    movementController: MovementController,
    actionsManager: ActionsManager,
    collisionsController: CollisionsController,
) : MobStrategy() where T : CloneableState<T>, T : MobState {
    init {
        this.randomlyRepeat(frequency, probability, actionsManager) {
            if (mobState.isAlive) {
                val position = mobState.position
                listOf(
                    Position(position.x - 1, position.y),
                    Position(position.x + 1, position.y),
                    Position(position.x, position.y - 1),
                    Position(position.x, position.y + 1)
                ).filter { movementController.canMoveTo(it) }
                    .filter { !collisionsController.isOccupied(it) }
                    .randomOrNull()?.run {
                        val newMobState = mobState.clone()
                        newMobState.position.replaceWith(this)

                        gameLevel.addCharacter(newMobState)
                        val newMobStrategy = ReproductiveStrategy(
                            newMobState,
                            frequency,
                            probability,
                            gameLevel,
                            movementController,
                            actionsManager,
                            collisionsController
                        )

                        MobController(
                            actionsManager, newMobState, movementController, newMobStrategy
                        ).apply {
                            register()
                            collisionsController.register(this)
                        }
                    }
            }
        }
    }

    override fun nextMovement(): Direction? = null
}