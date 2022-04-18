package ru.hse.sd.rogue.game.view.item

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Sprite
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.action.registerRepeatable
import ru.hse.sd.rogue.game.state.item.LootItemState
import ru.hse.sd.rogue.game.view.View
import ru.hse.sd.rogue.game.view.container.position

/**
 * Represents a view of item ax on the field.
 */
abstract class LootItemView(
    /**
     * [Container] to draw this view.
     */
    protected val container: Container,
    /**
     * Directly item.
     */
    protected open val item: LootItemState
) : View, IrreversibleAction {
    protected abstract val sprite: Sprite

    override fun invoke() {
        sprite.position(item.position)
    }

    override fun register(actionsManager: ActionsManager) {
        actionsManager.registerRepeatable(this)
    }
}
