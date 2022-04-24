package ru.hse.sd.rogue.game.logic.level.mobsfabric

import ru.hse.sd.rogue.game.logic.level.mobsfabric.MobsFactoryBase.PositionToState
import ru.hse.sd.rogue.game.state.character.*

/**
 * Standard dungeon mobs
 */
class ClassicDungeonMobsFactory : MobsFactoryBase() {
    override val easyMobs = listOf(
        PositionToState { TinyZombieMobState(it.asMutable()) },
        PositionToState { GoblinMobState(it.asMutable()) },
        PositionToState { ImpMobState(it.asMutable()) },
    )

    override val mediumMobs = listOf(
        PositionToState { SkeletonMobState(it.asMutable()) },
    )
    override val hardMobs = listOf(
        PositionToState { NecromancerMobState(it.asMutable()) },
        PositionToState { ReproductingMoldMobState(it.asMutable()) },
    )
    override val bossMob = PositionToState { BigDemonMobState(it.asMutable()) }
}