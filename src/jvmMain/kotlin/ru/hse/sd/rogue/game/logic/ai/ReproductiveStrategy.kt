package ru.hse.sd.rogue.game.logic.ai

import ru.hse.sd.rogue.game.controller.CollisionsController
import ru.hse.sd.rogue.game.controller.character.MobController
import ru.hse.sd.rogue.game.controller.character.MovementController
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.position.Direction
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.character.mob.MobState
import ru.hse.sd.rogue.game.state.character.mob.regular.ReproductingMoldMobState
import ru.hse.sd.rogue.game.view.character.mob.ReproductingMoldView
import ru.hse.sd.rogue.game.view.container.ContainersManager

class ReproductiveStrategy(mobState: MobState,
                           frequency: Long,
                           probability: Double,
                           movementController: MovementController,
                           actionsManager: ActionsManager,
                           collisionsController: CollisionsController,
                           containersManager: ContainersManager) : MobStrategy() {
    init {
        this.randomlyRepeat(frequency, probability, actionsManager) {
            val position = mobState.position
            listOf(Position(position.x - 1, position.y),
                    Position(position.x + 1, position.y),
                    Position(position.x, position.y - 1),
                    Position(position.x, position.y + 1)).filter { movementController.canMoveTo(it) }.randomOrNull()?.run {
                print("Create new mob to position ${this.x} ${this.y}")
                val newMobState = mobState.clone()
                newMobState.position.replaceWith(this)
                ReproductingMoldView(actionsManager, containersManager.characterContainer, newMobState as ReproductingMoldMobState)
                MobController(
                        actionsManager, newMobState, movementController, this@ReproductiveStrategy
                ).apply { collisionsController.register(this) }
            }
        }
    }

    override fun nextMovement(): Direction? = null
}