package ru.hse.sd.rogue.game.state.item.weapon

import ru.hse.sd.rogue.game.logic.item.Weapon
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.state.item.LootItemState

/**
 * Represents an weapon on the field.
 */
class LootWeaponState(
    override val item: Weapon,
    position: MutablePosition
) : LootItemState(item, position)
