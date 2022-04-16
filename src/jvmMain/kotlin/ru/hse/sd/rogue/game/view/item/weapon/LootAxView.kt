package ru.hse.sd.rogue.game.view.item.weapon

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.state.item.weapon.LootWeaponState
import ru.hse.sd.rogue.game.view.Tiles

class LootAxView(
    actionsManager: ActionsManager,
    container: Container,
    item: LootWeaponState
) : LootWeaponView(actionsManager, container, item) {
    override val sprite: Sprite = container.sprite(Tiles.Items.Weapons.ax, anchorY = 0.4)
}
