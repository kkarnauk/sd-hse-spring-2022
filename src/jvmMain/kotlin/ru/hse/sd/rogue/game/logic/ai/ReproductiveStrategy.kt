package ru.hse.sd.rogue.game.logic.ai

import ru.hse.sd.rogue.game.controller.CollisionsController
import ru.hse.sd.rogue.game.controller.MobViewFactory
import ru.hse.sd.rogue.game.controller.character.MobController
import ru.hse.sd.rogue.game.controller.character.MovementController
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.position.Direction
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.character.mob.MobState

class ReproductiveStrategy(
    mobState: MobState,
    frequency: Long,
    probability: Double,
    movementController: MovementController,
    actionsManager: ActionsManager,
    collisionsController: CollisionsController,
    mobViewFactory: MobViewFactory
) : MobStrategy() {
    init {
        this.randomlyRepeat(frequency, probability, actionsManager) {
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
                    with(mobViewFactory) {
                        newMobState.toView().also { it.register(actionsManager) }
                    }

                    MobController(
                        actionsManager, newMobState, movementController, this@ReproductiveStrategy
                    )
                        .also { it.register() }
                        .apply { collisionsController.register(this) }
                }
        }
    }

    override fun nextMovement(): Direction? = null
}