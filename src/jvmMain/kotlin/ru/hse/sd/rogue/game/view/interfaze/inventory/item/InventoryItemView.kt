package ru.hse.sd.rogue.game.view.interfaze.inventory.item

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.size
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.item.Item
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.view.View
import ru.hse.sd.rogue.game.view.container.position

/**
 * Represents a view of an item in the inventory.
 */
abstract class InventoryItemView(
    /**
     * [Container] to draw this.
     */
    protected val container: Container,
    /**
     * Directly item which is viewed.
     */
    protected open val item: Item,
    /**
     * Absolute position to draw this item.
     */
    private val position: Position
) : View, IrreversibleAction {
    protected abstract val sprite: Sprite

    override fun invoke() {
        sprite.position(position).size(12, 24)
    }

    /**
     * Removes this item view from the screen.
     */
    fun remove() {
        sprite.removeFromParent()
    }
}
