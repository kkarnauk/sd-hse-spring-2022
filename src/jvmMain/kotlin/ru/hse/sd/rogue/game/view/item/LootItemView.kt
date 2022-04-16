package ru.hse.sd.rogue.game.view.item

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Sprite
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.state.item.LootItemState
import ru.hse.sd.rogue.game.view.View
import ru.hse.sd.rogue.game.view.container.position

abstract class LootItemView(
    protected val container: Container,
    protected open val item: LootItemState
) : View, IrreversibleAction {
    protected abstract val sprite: Sprite

    override fun invoke() {
        sprite.position(item.position)
    }
}
