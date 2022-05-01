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
    maxChildren: Int,
    mobState: T,
    frequency: Long,
    probability: Double,
    gameLevel: Level,
    movementController: MovementController,
    actionsManager: ActionsManager,
    collisionsController: CollisionsController,
) : MobStrategy() where T : CloneableState<T>, T : MobState {
    private class ChildrenCounter {
        var childrenCount: Int = 1
    }

    private var childrenCounter = ChildrenCounter()
    private var deadSignalSent = false

    init {
        this.randomlyRepeat(frequency, probability, actionsManager) {
            if (mobState.isAlive) {
                if (childrenCounter.childrenCount <= maxChildren) {
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
                                maxChildren,
                                newMobState,
                                frequency,
                                probability,
                                gameLevel,
                                movementController,
                                actionsManager,
                                collisionsController
                            )
                            childrenCounter.childrenCount++
                            newMobStrategy.childrenCounter = childrenCounter

                            MobController(
                                actionsManager, newMobState, movementController, newMobStrategy
                            ).apply {
                                register()
                                collisionsController.register(this)
                            }
                        }
                }
            } else {
                if (!deadSignalSent) {
                    childrenCounter.childrenCount--
                    deadSignalSent = true
                }
            }
        }
    }

    override fun nextMovement(): Direction? = null
}