package ru.hse.sd.rogue.game.view.item.weapon

import com.soywiz.korge.view.Container
import ru.hse.sd.rogue.game.state.item.LootItemState
import ru.hse.sd.rogue.game.view.item.LootItemView

/**
 * Represents a view of weapon ax on the field.
 */
abstract class LootWeaponView(
    container: Container,
    item: LootItemState
) : LootItemView(container, item) {
}
