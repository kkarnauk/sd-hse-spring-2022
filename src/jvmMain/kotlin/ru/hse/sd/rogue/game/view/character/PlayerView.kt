package ru.hse.sd.rogue.game.view.character

import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.position
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.logic.action.registerRepeatable
import ru.hse.sd.rogue.game.state.character.PlayerState
import ru.hse.sd.rogue.game.view.Tiles
import ru.hse.sd.rogue.game.view.cellSize

class PlayerView(
    actionsManager: ActionsManager,
    container: Container,
    characterState: PlayerState
) : CharacterView(actionsManager, container, characterState) {
    init {
        actionsManager.registerRepeatable(this)
    }

    private val playerSprite = run {
        container.sprite(Tiles.Player.idle, anchorY = 0.6).also {
            it.playAnimationLooped(spriteDisplayTime = 70.milliseconds)
        }
    }

    override fun invoke() {
        playerSprite.position(characterState.position.x * cellSize, characterState.position.y * cellSize)
    }
}
