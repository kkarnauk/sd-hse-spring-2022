package ru.hse.sd.rogue.game.view.interfaze.inventory.item

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.size
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.item.Item
import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.view.View
import ru.hse.sd.rogue.game.view.container.position

abstract class InventoryItemView(
    protected val container: Container,
    protected open val item: Item,
    private val position: Position
) : View, IrreversibleAction {
    protected abstract val sprite: Sprite

    override fun invoke() {
        sprite.position(position).size(12, 24)
    }

    fun remove() {
        sprite.removeFromParent()
    }
}
