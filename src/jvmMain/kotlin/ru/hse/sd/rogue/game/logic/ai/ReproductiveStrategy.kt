package ru.hse.sd.rogue.game.logic.ai

import ru.hse.sd.rogue.game.controller.character.MovementController
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.position.Direction
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.character.mob.MobState

class ReproductiveStrategy(mobState: MobState,
                           frequency: Long,
                           probability: Double,
                           movementController: MovementController,
                           actionsManager: ActionsManager) : MobStrategy {
    init {
        this.randomlyRepeat(frequency, probability, actionsManager, IrreversibleAction {
            val position = mobState.position
            val possiblePositions = listOf(Position(position.x - 1, position.y),
                    Position(position.x + 1, position.y),
                    Position(position.x, position.y - 1),
                    Position(position.x, position.y + 1)).filter { movementController.canMoveTo(it) }.randomOrNull()?.run {
                mobState.clone()
            }
        })
    }

    override fun nextMovement(): Direction? = null
}