package ru.hse.sd.rogue.game.logic.level.mobsfabric

import ru.hse.sd.rogue.game.logic.level.mobsfabric.MobsFactoryBase.PositionToState
import ru.hse.sd.rogue.game.state.character.mob.boss.RinoMobState
import ru.hse.sd.rogue.game.state.character.mob.regular.*

/**
 * Animal mobs
 */
class AnimalMobsFactory : MobsFactoryBase() {
    override val easyMobs = listOf(
        PositionToState { MushroomMobState(it.asMutable()) },
        PositionToState { SlimeMobState(it.asMutable()) }
    )

    override val mediumMobs = listOf(
        PositionToState { BunnyMobState(it.asMutable()) }
    )
    override val hardMobs = listOf(
        PositionToState { ChameleonMobState(it.asMutable()) },
        PositionToState { AngryPigMobState(it.asMutable()) }
    )
    override val bossMob = PositionToState { RinoMobState(it.asMutable()) }
}