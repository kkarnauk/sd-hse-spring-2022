package ru.hse.sd.rogue.game.logic.item

import ru.hse.sd.rogue.game.controller.character.CharacterController
import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Durability

/**
 * Weapon that characters can use to increase their damage.
 */
data class Weapon(
    /**
     * [Damage] of this weapon.
     */
    val damage: Damage,
    /**
     * [Durability] of this weapon.
     */
    val durability: Durability,
    val type: Type
) : Item() {
    override val usable: Boolean
        get() = durability.current > 0

    enum class Type {
        Sword,
        Ax
    }
}
