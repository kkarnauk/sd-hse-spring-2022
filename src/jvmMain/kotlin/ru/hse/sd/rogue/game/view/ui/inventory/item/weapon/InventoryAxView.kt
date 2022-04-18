package ru.hse.sd.rogue.game.view.ui.inventory.item.weapon

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.logic.item.Weapon
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.view.Tiles

/**
 * Represents a view of an ax in the inventory.
 */
class InventoryAxView(
    container: Container,
    item: Weapon,
    position: Position
) : InventoryWeaponView(container, item, position) {
    override val sprite: Sprite = container.sprite(Tiles.Items.Weapons.ax, anchorY = 0.4, anchorX = -0.1)
}
