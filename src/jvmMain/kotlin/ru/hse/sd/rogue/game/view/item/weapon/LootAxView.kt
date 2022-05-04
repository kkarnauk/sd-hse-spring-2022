package ru.hse.sd.rogue.game.view.item.weapon

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.state.item.LootItemState
import ru.hse.sd.rogue.game.view.Tiles

/**
 * Represents a view of an ax on the field.
 */
class LootAxView(
    container: Container,
    item: LootItemState
) : LootWeaponView(container, item) {
    override val sprite: Sprite = container.sprite(Tiles.Items.Weapons.ax, anchorY = 0.4)
}
