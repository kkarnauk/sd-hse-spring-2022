package ru.hse.sd.rogue.game.view.character.player

import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.state.character.PlayerState
import ru.hse.sd.rogue.game.view.Tiles
import ru.hse.sd.rogue.game.view.character.CharacterView

class PlayerView(
    actionsManager: ActionsManager,
    container: Container,
    characterState: PlayerState
) : CharacterView(actionsManager, container, characterState) {

    override val sprite = container.sprite(
        Tiles.Player.idle,
        anchorY = 0.6
    ).also {
        it.playAnimationLooped(spriteDisplayTime = 70.milliseconds)
    }
}
