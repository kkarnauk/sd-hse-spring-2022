package ru.hse.sd.rogue.game.state.item

import ru.hse.sd.rogue.game.logic.item.Item
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.state.CollisableState

/**
 * Represents a state of an item on the field.
 */
abstract class LootItemState(
    /**
     * Directly the item on the field.
     */
    open val item: Item,
    /**
     * Position of this item.
     */
    override val position: MutablePosition
) : CollisableState
