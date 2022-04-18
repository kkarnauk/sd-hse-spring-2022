package ru.hse.sd.rogue.game.view.item.potion

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.state.item.weapon.LootPotionState
import ru.hse.sd.rogue.game.view.Tiles
import ru.hse.sd.rogue.game.view.item.weapon.LootWeaponView

class LootBluePotionView(
    container: Container,
    item: LootPotionState
) : LootPotionView(container, item) {
    override val sprite: Sprite = container.sprite(Tiles.Items.Potion.blue, anchorY = -0.2, anchorX = -0.3)
}
