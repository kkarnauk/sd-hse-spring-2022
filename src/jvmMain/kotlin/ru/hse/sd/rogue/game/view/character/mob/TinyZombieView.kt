package ru.hse.sd.rogue.game.view.character.mob

import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.state.character.CharacterState
import ru.hse.sd.rogue.game.view.Tiles
import ru.hse.sd.rogue.game.view.character.CharacterView

/**
 * View of a tiny zombie.
 */
class TinyZombieView(
    container: Container,
    characterState: CharacterState
) : CharacterView(container, characterState) {

    override val sprite = container
        .sprite(
            Tiles.Mobs.TinyZombie.idle,
            anchorX = 0.0,
            anchorY = 0.3
        ).also {
            it.playAnimationLooped(spriteDisplayTime = 70.milliseconds)
        }
}
