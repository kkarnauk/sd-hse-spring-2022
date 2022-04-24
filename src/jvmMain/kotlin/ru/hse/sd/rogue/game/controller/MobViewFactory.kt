package ru.hse.sd.rogue.game.controller

import com.soywiz.korge.view.Container
import ru.hse.sd.rogue.game.state.character.*
import ru.hse.sd.rogue.game.view.character.CharacterView
import ru.hse.sd.rogue.game.view.character.mob.*

class MobViewFactory(val characterContainer: Container): Controller {
    fun MobState.toView(): CharacterView {
        return when (this) {
            is AngryPigMobState -> AngryPigView(characterContainer, this)
            is BigDemonMobState -> BigDemonView(characterContainer, this)
            is BunnyMobState -> BunnyView(characterContainer, this)
            is ChameleonMobState -> ChameleonView(characterContainer, this)
            is GoblinMobState -> GoblinView(characterContainer, this)
            is ImpMobState -> ImpView(characterContainer, this)
            is MushroomMobState -> MushroomView(characterContainer, this)
            is NecromancerMobState -> NecromancerView(characterContainer, this)
            is ReproductingMoldMobState -> ReproductingMoldView(characterContainer, this)
            is RinoMobState -> RinoView(characterContainer, this)
            is SkeletonMobState -> SkeletonView(characterContainer, this)
            is SlimeMobState -> SlimeView(characterContainer, this)
            is TinyZombieMobState -> TinyZombieView(characterContainer, this)
        }
    }
}