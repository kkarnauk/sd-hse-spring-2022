package ru.hse.sd.rogue.game.view.item.weapon

import com.soywiz.korge.view.Container
import ru.hse.sd.rogue.game.state.item.weapon.LootWeaponState
import ru.hse.sd.rogue.game.view.item.LootItemView

/**
 * Represents a view of weapon ax on the field.
 */
abstract class LootWeaponView(
    container: Container,
    override val item: LootWeaponState
) : LootItemView(container, item) {
}
