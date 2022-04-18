package ru.hse.sd.rogue.game.view.ui.inventory.item.weapon

import com.soywiz.korge.view.Container
import ru.hse.sd.rogue.game.logic.item.Weapon
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.view.ui.inventory.item.InventoryItemView

/**
 * Represents a view of a weapon in the inventory.
 */
abstract class InventoryWeaponView(
    container: Container,
    override val item: Weapon,
    position: Position
) : InventoryItemView(container, item, position)
