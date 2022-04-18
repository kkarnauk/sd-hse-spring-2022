package ru.hse.sd.rogue.game.controller.item

import ru.hse.sd.rogue.game.controller.CollisableController
import ru.hse.sd.rogue.game.state.item.LootItemState

/**
 * Represents a controller for an available item on the map.
 */
class LootItemController(
    /**
     * State of this item.
     */
    override val state: LootItemState
) : CollisableController {
    override fun collideWith(other: CollisableController) = Unit

    override fun noCollisions() = Unit
}
