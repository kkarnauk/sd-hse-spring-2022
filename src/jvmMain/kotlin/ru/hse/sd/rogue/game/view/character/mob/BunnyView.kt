package ru.hse.sd.rogue.game.view.character.mob

import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.state.character.mob.BunnyMobState
import ru.hse.sd.rogue.game.view.Tiles
import ru.hse.sd.rogue.game.view.character.CharacterView

/**
 * View of a bunny.
 */
class BunnyView(
    container: Container,
    characterState: BunnyMobState,
) : CharacterView(container, characterState) {

    override val sprite = container
        .sprite(
            Tiles.Mobs.Bunny.idle,
            anchorX = 1.0,
            anchorY = 0.4
        ).also {
            it.scale = 0.45
            it.scaleX *= -1.0
            it.playAnimationLooped(spriteDisplayTime = 50.milliseconds)
        }
}
