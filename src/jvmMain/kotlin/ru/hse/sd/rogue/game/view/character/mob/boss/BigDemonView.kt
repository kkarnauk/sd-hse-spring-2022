package ru.hse.sd.rogue.game.view.character.mob.boss

import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.state.character.mob.boss.BigDemonMobState
import ru.hse.sd.rogue.game.view.Tiles
import ru.hse.sd.rogue.game.view.character.CharacterView

/**
 * View of the boss.
 */
class BigDemonView(
    container: Container,
    characterState: BigDemonMobState,
) : CharacterView(container, characterState) {

    override val sprite = container
        .sprite(
            Tiles.Mobs.BigDemon.idle,
            anchorX = 0.25,
            anchorY = 0.65
        ).also {
            it.playAnimationLooped(spriteDisplayTime = 70.milliseconds)
        }
}
