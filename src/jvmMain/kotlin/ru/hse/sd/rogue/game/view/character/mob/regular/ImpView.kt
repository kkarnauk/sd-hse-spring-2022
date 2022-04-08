package ru.hse.sd.rogue.game.view.character.mob.regular

import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.state.character.mob.regular.ImpMobState
import ru.hse.sd.rogue.game.view.Tiles
import ru.hse.sd.rogue.game.view.character.CharacterView

/**
 * View of an impl.
 */
class ImpView(
    actionsManager: ActionsManager,
    container: Container,
    characterState: ImpMobState,
) : CharacterView(actionsManager, container, characterState) {

    override val sprite = container
        .sprite(
            Tiles.Mobs.Imp.idle,
            anchorX = 0.2,
            anchorY = 0.3
        ).also {
            it.playAnimationLooped(spriteDisplayTime = 70.milliseconds)
        }
}
