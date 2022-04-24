package ru.hse.sd.rogue.game.view.character.mob

import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.state.character.mob.RinoMobState
import ru.hse.sd.rogue.game.view.Tiles
import ru.hse.sd.rogue.game.view.character.CharacterView

/**
 * View of the rino.
 */
class RinoView(
    container: Container,
    characterState: RinoMobState,
) : CharacterView(container, characterState) {

    override val sprite = container
        .sprite(
            Tiles.Mobs.Rino.idle,
            anchorX = 1.0,
            anchorY = 0.3
        ).also {
            it.scale = 0.4
            it.scaleX *= -1.0
            it.playAnimationLooped(spriteDisplayTime = 50.milliseconds)
        }
}
