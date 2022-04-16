package ru.hse.sd.rogue.game.view.item.weapon

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.state.item.weapon.LootWeaponState
import ru.hse.sd.rogue.game.view.Tiles

class LootSwordView(
    actionsManager: ActionsManager,
    container: Container,
    item: LootWeaponState
) : LootWeaponView(container, item) {
    override val sprite: Sprite = container.sprite(Tiles.Items.Weapons.sword, anchorY = 0.3)
}
