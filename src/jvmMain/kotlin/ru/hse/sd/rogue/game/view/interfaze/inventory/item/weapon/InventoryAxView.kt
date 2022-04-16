package ru.hse.sd.rogue.game.view.interfaze.inventory.item.weapon

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.sprite
import com.soywiz.korma.geom.Rectangle
import ru.hse.sd.rogue.game.logic.item.Weapon
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.view.Tiles

class InventoryAxView(
    container: Container,
    item: Weapon,
    position: Position
) : InventoryWeaponView(container, item, position) {
    override val sprite: Sprite = container.sprite(Tiles.Items.Weapons.ax, anchorY = 0.4).apply {
        Rectangle()
    }
}
