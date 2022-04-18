package ru.hse.sd.rogue.game.controller.character

import ru.hse.sd.rogue.game.controller.CollisableController
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.action.registerRepeatable
import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.common.Effect
import ru.hse.sd.rogue.game.logic.position.Direction
import ru.hse.sd.rogue.game.logic.position.LookDirection
import ru.hse.sd.rogue.game.state.character.CharacterState

/**
 * Responsible for controlling actions with characters (player and mobs).
 * For now, controls movement and taking damage.
 *
 * Also, responsible for taking actions on characters and send them to [ActionsManager] (see [update]).
 */
abstract class CharacterController(
    /**
     * General actions' manager used to change characters states.
     */
    protected val actionsManager: ActionsManager,
    /**
     * [CharacterState] of this character.
     */
    override val state: CharacterState,
    /**
     * Checks whether this character can move to one or another position.
     */
    protected val movementController: MovementController,
    /**
     * Experience gained when killing this character
     */
    val xpForKilling: Int = 10
) : CollisableController {
    /**
     * Tries to move a character into [direction].
     */
    fun move(direction: Direction) {
        val newPosition = when (direction) {
            Direction.Up -> state.position.decY()
            Direction.Down -> state.position.incY()
            Direction.Left -> state.position.decX()
            Direction.Right -> state.position.incX()
        }
        val newLookDirection = when (direction) {
            Direction.Left -> LookDirection.Left
            Direction.Right -> LookDirection.Right
            else -> state.lookDirection
        }
        update {
            if (movementController.canMoveTo(newPosition)) {
                state.position.replaceWith(newPosition)
            }
            state.lookDirection = newLookDirection
        }
    }

    /**
     * Use this method if you want to change a state of a character somehow.
     * It's forbidden to change anything without calling [update] or [updateRepeatable].
     */
    fun update(act: CharacterController.() -> Unit) {
        actionsManager.register(IrreversibleAction { act(this) })
    }

    /**
     * Use this method if you want to change a state of a character somehow repeatable.
     * It's forbidden to change anything without calling [update] or [updateRepeatable].
     */
    fun updateRepeatable(act: CharacterController.() -> Unit) {
        actionsManager.registerRepeatable { act(this) }
    }

    /**
     * Updates current [CharacterState.health] with considering one application of [damage].
     * Updates [CharacterState.isAlive] depending on the result health.
     */
    fun takeDamage(damage: Damage, effects: List<Effect>) = update {
        if (!state.isAlive) {
            return@update
        }
        state.health.current -= damage.value
        effects.forEach { apply(it) }
    }

    override fun noCollisions() = Unit

    abstract fun apply(effect: Effect)
}
