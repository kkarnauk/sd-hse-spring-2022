package ru.hse.sd.rogue.game.view.character

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Sprite
import com.soywiz.korma.geom.plus
import com.soywiz.korma.geom.radians
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.logic.action.registerRepeatable
import ru.hse.sd.rogue.game.logic.position.LookDirection
import ru.hse.sd.rogue.game.logic.position.opposite
import ru.hse.sd.rogue.game.logic.position.takeAway
import ru.hse.sd.rogue.game.state.character.CharacterState
import ru.hse.sd.rogue.game.view.View
import ru.hse.sd.rogue.game.view.container.position
import kotlin.math.PI

/**
 * View of a character.
 */
abstract class CharacterView(/**
     * [Container] to contain a sprite of a character.
     */
    protected val container: Container,
    /**
     * [CharacterState] of this character.
     */
    protected val characterState: CharacterState
) : View, IrreversibleAction {
    protected abstract val sprite: Sprite
    protected var currentLookDirection: LookDirection = LookDirection.Right

    private fun rotate() {
        currentLookDirection = currentLookDirection.opposite()
        sprite.rotation += PI.radians
        sprite.skewX += PI.radians
        sprite.anchorX = 1 - sprite.anchorX
    }

    override fun invoke() {
        if (!characterState.isAlive) {
            characterState.position.takeAway()
        }

        sprite.position(characterState.position)
        if (characterState.lookDirection != currentLookDirection) {
            rotate()
        }
    }

    override fun register(actionsManager: ActionsManager) {
        actionsManager.registerRepeatable(this)
    }
}