package ru.hse.sd.rogue.game.state.item

import ru.hse.sd.rogue.game.logic.item.Item
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.state.CollisableState

abstract class LootItemState(
    open val item: Item,
    override val position: MutablePosition
) : CollisableState
