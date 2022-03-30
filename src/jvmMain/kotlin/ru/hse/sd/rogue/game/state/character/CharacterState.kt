package ru.hse.sd.rogue.game.state.character

import ru.hse.sd.rogue.game.controller.CharacterController
import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.item.Armor
import ru.hse.sd.rogue.game.logic.item.Item
import ru.hse.sd.rogue.game.logic.item.Potion
import ru.hse.sd.rogue.game.logic.item.Weapon
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.State

abstract class CharacterState(
    val health: Health,
    var position: Position,
    protected var meleeDamage: Damage,
    initItems: MutableList<Item> = mutableListOf()
) : State {
    var isAlive: Boolean = true
        private set

    protected val items: MutableList<Item> = initItems

    protected val weapons: List<Weapon>
        get() = items.filterIsInstance<Weapon>()

    protected val armors: List<Armor>
        get() = items.filterIsInstance<Armor>()

    protected val potions: List<Potion>
        get() = items.filterIsInstance<Potion>()

    var currentWeapon: Weapon? = null

    val damage: Damage
        get() = currentWeapon?.damage ?: meleeDamage

    protected abstract val controller: CharacterController

    fun update(act: CharacterState.() -> Unit) {
        controller.action { act(this) }
    }

    fun takeDamage(damage: Damage) = update {
        if (!isAlive) {
            return@update
        }
        health.current -= damage.value
        if (health.current <= 0) {
            isAlive = false
        }
    }

    // TODO
}
