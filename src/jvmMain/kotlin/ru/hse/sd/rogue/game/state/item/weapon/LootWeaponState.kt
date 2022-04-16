package ru.hse.sd.rogue.game.state.item.weapon

import ru.hse.sd.rogue.game.logic.item.Weapon
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.item.LootItemState

class LootWeaponState(
    override val item: Weapon,
    position: Position
) : LootItemState(item, position)
