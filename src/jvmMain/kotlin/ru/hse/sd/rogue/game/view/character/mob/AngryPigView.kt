package ru.hse.sd.rogue.game.view.character.mob

import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.state.character.mob.AngryPigMobState
import ru.hse.sd.rogue.game.view.Tiles
import ru.hse.sd.rogue.game.view.character.CharacterView

/**
 * View of an angry pig.
 */
class AngryPigView(
    container: Container,
    characterState: AngryPigMobState,
) : CharacterView(container, characterState) {

    override val sprite = container
        .sprite(
            Tiles.Mobs.AngryPig.idle,
            anchorX = 0.95,
            anchorY = 0.2
        ).also {
            it.scale = 0.5
            it.scaleX *= -1.0
            it.playAnimationLooped(spriteDisplayTime = 50.milliseconds)
        }
}
