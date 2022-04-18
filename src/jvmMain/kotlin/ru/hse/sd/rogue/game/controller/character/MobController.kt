package ru.hse.sd.rogue.game.controller.character

import ru.hse.sd.rogue.game.controller.CollisableController
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.ai.MobStrategy
import ru.hse.sd.rogue.game.logic.common.Effect
import ru.hse.sd.rogue.game.state.character.CharacterState

/**
 * Responsible for controlling mobs.
 */
class MobController(
    actionsManager: ActionsManager,
    state: CharacterState,
    movementController: MovementController,
    private val mobStrategy: MobStrategy
) : CharacterController(actionsManager, state, movementController) {
    override fun collideWith(other: CollisableController) {
        if (other is PlayerController) {
            other.takeDamage(state.damage, state.effects)
        }
    }

    fun register() {
        updateRepeatable {
            mobStrategy.nextMovement()?.let { move(it) }
        }
    }

    override fun apply(effect: Effect) {
        mobStrategy.effect = effect
    }
}
