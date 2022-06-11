package ru.hse.sd.rogue.game.view.item.weapon

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.state.item.LootItemState
import ru.hse.sd.rogue.game.view.Tiles

/**
 * Represents a view of a sword on the field.
 */
class LootSwordView(
    container: Container,
    item: LootItemState
) : LootWeaponView(container, item) {
    override val sprite: Sprite = container.sprite(Tiles.Items.Weapons.sword, anchorY = 0.3)
}
