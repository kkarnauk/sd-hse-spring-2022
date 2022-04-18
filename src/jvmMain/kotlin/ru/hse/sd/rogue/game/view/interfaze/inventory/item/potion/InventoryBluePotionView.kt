package ru.hse.sd.rogue.game.view.interfaze.inventory.item.potion

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.logic.item.Potion
import ru.hse.sd.rogue.game.logic.item.Weapon
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.view.Tiles
import ru.hse.sd.rogue.game.view.interfaze.inventory.item.weapon.InventoryWeaponView

/**
 * Represents a view of a blue potion in the inventory.
 */
class InventoryBluePotionView(
    container: Container,
    item: Potion,
    position: Position
) : InventoryPotionView(container, item, position) {
    override val sprite: Sprite = container.sprite(Tiles.Items.Potion.blue, anchorY = 0.4, anchorX = -0.1)
}
