package ru.hse.sd.rogue.game.view.item.potion

import com.soywiz.korge.view.Container
import ru.hse.sd.rogue.game.state.item.weapon.LootPotionState
import ru.hse.sd.rogue.game.state.item.weapon.LootWeaponState
import ru.hse.sd.rogue.game.view.item.LootItemView

/**
 * Represents a view of weapon ax on the field.
 */
abstract class LootPotionView(
    container: Container,
    override val item: LootPotionState
) : LootItemView(container, item) {
}
