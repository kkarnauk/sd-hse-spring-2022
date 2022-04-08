package ru.hse.sd.rogue.game.controller.character

import ru.hse.sd.rogue.game.controller.Controller
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.item.Weapon
import ru.hse.sd.rogue.game.logic.position.Direction
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
    protected open val state: CharacterState,
    /**
     * Checks whether this character can move to one or another position.
     */
    protected val movementController: MovementController
) : Controller {
    /**
     * Tries to move a character into [direction].
     */
    abstract fun move(direction: Direction)

    /**
     * Use this method if you want to change a state of a character somehow.
     * It's forbidden to change anything without calling [update].
     */
    fun update(act: CharacterController.() -> Unit) {
        actionsManager.register(IrreversibleAction { act(this) })
    }

    /**
     * Changes [CharacterState.currentWeapon] to [newWeapon].
     */
    fun takeWeapon(newWeapon: Weapon) {
        state.currentWeapon = newWeapon
    }

    /**
     * Updates current [CharacterState.health] with considering one application of [damage].
     * Updates [CharacterState.isAlive] depending on the result health.
     */
    fun takeDamage(damage: Damage) = update {
        if (!state.isAlive) {
            return@update
        }
        state.health.current -= damage.value
    }
}
