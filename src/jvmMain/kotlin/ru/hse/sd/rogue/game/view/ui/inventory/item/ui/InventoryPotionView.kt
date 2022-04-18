package ru.hse.sd.rogue.game.view.ui.inventory.item.ui

import com.soywiz.korge.view.Container
import ru.hse.sd.rogue.game.logic.item.Potion
import ru.hse.sd.rogue.game.logic.item.Weapon
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.view.ui.inventory.item.InventoryItemView

/**
 * Represents a view of a potion in the inventory.
 */
abstract class InventoryPotionView(
    container: Container,
    override val item: Potion,
    position: Position
) : InventoryItemView(container, item, position)
