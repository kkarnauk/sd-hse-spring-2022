package ru.hse.sd.rogue.game.controller.character

import ru.hse.sd.rogue.game.controller.Controller
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.item.Weapon
import ru.hse.sd.rogue.game.logic.position.Direction
import ru.hse.sd.rogue.game.state.character.CharacterState

abstract class CharacterController(
    protected val actionsManager: ActionsManager,
    protected open val state: CharacterState
) : Controller {
    abstract fun move(direction: Direction)

    fun action(act: () -> Unit) {
        actionsManager.register(CharacterAction(act))
    }

    fun update(act: CharacterController.() -> Unit) {
        action { act(this) }
    }
    
    fun takeWeapon(newWeapon: Weapon) {
        state.currentWeapon = newWeapon
    }

    fun takeDamage(damage: Damage) = update {
        if (!state.isAlive) {
            return@update
        }
        state.health.current -= damage.value
    }
}

class CharacterAction(private val action: () -> Unit) : IrreversibleAction {
    override fun invoke() = action()
}
