package ru.hse.sd.rogue.game.view.character.mob

import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.sprite
import ru.hse.sd.rogue.game.logic.action.ActionsManager
import ru.hse.sd.rogue.game.state.character.mob.regular.ReproductingMoldMobState
import ru.hse.sd.rogue.game.view.Tiles
import ru.hse.sd.rogue.game.view.character.CharacterView

class ReproductingMoldView(
        actionsManager: ActionsManager,
        container: Container,
        characterState: ReproductingMoldMobState,
) : CharacterView(actionsManager, container, characterState) {
    override val sprite = container
            .sprite(
                    Tiles.Mobs.ReproductingMold.idle,
                    anchorX = 0.2,
                    anchorY = 0.3
            ).also {
                it.playAnimationLooped(spriteDisplayTime = 70.milliseconds)
            }
}