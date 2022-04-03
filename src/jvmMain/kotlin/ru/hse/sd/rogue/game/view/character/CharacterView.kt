package ru.hse.sd.rogue.game.view.character

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.position
import com.soywiz.korma.geom.plus
import com.soywiz.korma.geom.radians
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.action.registerRepeatable
import ru.hse.sd.rogue.game.logic.position.LookDirection
import ru.hse.sd.rogue.game.logic.position.opposite
import ru.hse.sd.rogue.game.state.character.CharacterState
import ru.hse.sd.rogue.game.view.View
import ru.hse.sd.rogue.game.view.cellSize
import kotlin.math.PI

abstract class CharacterView(
    actionsManager: ActionsManager,
    protected val container: Container,
    protected val characterState: CharacterState
) : View, IrreversibleAction {

    init {
        actionsManager.registerRepeatable(this)
    }

    protected abstract val sprite: Sprite
    protected var currentLookDirection: LookDirection = LookDirection.Right

    private fun rotate() {
        currentLookDirection = currentLookDirection.opposite()
        sprite.rotation += PI.radians
        sprite.skewX += PI.radians
        sprite.anchorX = 1 - sprite.anchorX
    }

    override fun invoke() {
        sprite.position(characterState.position.x * cellSize, characterState.position.y * cellSize)
        if (characterState.lookDirection != currentLookDirection) {
            rotate()
        }
    }
}