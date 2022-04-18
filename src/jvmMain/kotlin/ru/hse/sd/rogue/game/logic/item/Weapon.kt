package ru.hse.sd.rogue.game.logic.item

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Durability
import ru.hse.sd.rogue.game.logic.common.Effect
import ru.hse.sd.rogue.game.logic.item.Weapon.Type

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
    /**
     * [Type] of this weapon.
     */
    val type: Type,
    /**
     * List of [Effect] of this weapon.
     */
    val effects: List<Effect> = emptyList()
) : Item() {
    override val usable: Boolean
        get() = durability.current > 0

    /**
     * Different types of [Weapon].
     */
    enum class Type {
        Sword,
        Ax
    }
}
