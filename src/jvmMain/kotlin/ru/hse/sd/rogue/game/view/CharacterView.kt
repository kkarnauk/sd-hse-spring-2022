package ru.hse.sd.rogue.game.view

import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.position
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.logic.action.IrreversibleAction
import ru.hse.sd.rogue.game.state.MapState

class CharacterView(container: Container, private val mapState: MapState) : IrreversibleAction {
    private val playerSprite = container.sprite(Tiles.Player.idle, anchorY = 0.6)
        .also {
            it.playAnimationLooped(spriteDisplayTime = 70.milliseconds)
        }

    override fun invoke() {
        mapState.playerCell?.position?.let { position ->
            playerSprite.position(position.x * cellSize, position.y * cellSize)
        }
    }
}