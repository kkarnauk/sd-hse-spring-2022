package ru.hse.sd.rogue.game.state.character

import ru.hse.sd.rogue.game.logic.characteristics.MutableDamage
import ru.hse.sd.rogue.game.logic.characteristics.MutableExperience
import ru.hse.sd.rogue.game.logic.characteristics.MutableHealth
import ru.hse.sd.rogue.game.logic.common.Effect
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.state.InventoryMutableState

/**
 * State of the player.
 */
class PlayerState(
    health: MutableHealth,
    position: MutablePosition,
    meleeDamage: MutableDamage,
    val inventoryState: InventoryMutableState
) : CharacterMutableState(health, position, meleeDamage) {
    override val damage: MutableDamage
        get() = inventoryState.currentWeapon?.damage?.asMutable() ?: meleeDamage

    val experience = MutableExperience(0, 1)

    override val effects: List<Effect>
        get() = inventoryState.currentWeapon?.effects ?: emptyList()
}
