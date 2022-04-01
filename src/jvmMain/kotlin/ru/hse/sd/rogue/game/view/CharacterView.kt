package ru.hse.sd.rogue.game.view

import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.position
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.state.character.CharacterState

class CharacterView(
    container: Container,
    private val characterState: CharacterState
) : View, IrreversibleAction {
    private val playerSprite = container.sprite(Tiles.Player.idle, anchorY = 0.6)
        .also {
            it.playAnimationLooped(spriteDisplayTime = 70.milliseconds)
        }

    override fun invoke() {
        playerSprite.position(characterState.position.x * cellSize, characterState.position.y * cellSize)
    }
}