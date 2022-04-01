package ru.hse.sd.rogue.game.state.character

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
    val position: Position,
    protected var meleeDamage: Damage,
    initItems: List<Item> = emptyList()
) : State {
    val isAlive: Boolean
        get() = health.current > 0

    protected val items: MutableList<Item> = initItems.toMutableList()

    protected val weapons: List<Weapon>
        get() = items.filterIsInstance<Weapon>()

    protected val armors: List<Armor>
        get() = items.filterIsInstance<Armor>()

    protected val potions: List<Potion>
        get() = items.filterIsInstance<Potion>()

    var currentWeapon: Weapon? = null

    val damage: Damage
        get() = currentWeapon?.damage ?: meleeDamage
}
