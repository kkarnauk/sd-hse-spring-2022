package ru.hse.sd.rogue.game.view.item.weapon

import com.soywiz.korge.view.Container
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.state.item.weapon.LootWeaponState
import ru.hse.sd.rogue.game.view.item.LootItemView

/**
 * Represents a view of weapon ax on the field.
 */
abstract class LootWeaponView(
    actionsManager: ActionsManager,
    container: Container,
    override val item: LootWeaponState
) : LootItemView(actionsManager, container, item) {
}
