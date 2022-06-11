package ru.hse.sd.rogue.game.view.item.potion

import com.soywiz.korge.view.Container
import ru.hse.sd.rogue.game.state.item.LootItemState
import ru.hse.sd.rogue.game.view.item.LootItemView

/**
 * Represents a view of weapon ax on the field.
 */
abstract class LootPotionView(
    container: Container,
    override val item: LootItemState
) : LootItemView(container, item)
