package ru.hse.sd.rogue.game.state.item.weapon

import ru.hse.sd.rogue.game.logic.item.Potion
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.state.item.LootItemMutableState

class LootPotionState(
    override val item: Potion,
    position: MutablePosition
) : LootItemMutableState(item, position)
