package ru.hse.sd.rogue.game.view.character.mob

import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.state.character.MushroomMobState
import ru.hse.sd.rogue.game.view.Tiles
import ru.hse.sd.rogue.game.view.character.CharacterView

/**
 * View of a mushroom.
 */
class MushroomView(
    container: Container,
    characterState: MushroomMobState,
) : CharacterView(container, characterState) {

    override val sprite = container
        .sprite(
            Tiles.Mobs.Mushroom.idle,
            anchorX = 1.4,
            anchorY = 0.0
        ).also {
            it.scale = 0.3
            it.scaleX *= -1.0
            it.playAnimationLooped(spriteDisplayTime = 50.milliseconds)
        }
}
