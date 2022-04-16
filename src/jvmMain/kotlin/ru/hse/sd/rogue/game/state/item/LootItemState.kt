package ru.hse.sd.rogue.game.state.item

import ru.hse.sd.rogue.game.logic.item.Item
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.State

abstract class LootItemState(
    open val item: Item,
    val position: Position
) : State
