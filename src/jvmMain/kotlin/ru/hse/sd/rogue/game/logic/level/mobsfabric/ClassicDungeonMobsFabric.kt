package ru.hse.sd.rogue.game.logic.level.mobsfabric

import ru.hse.sd.rogue.game.logic.level.mobsfabric.MobsFabricBase.PositionToState
import ru.hse.sd.rogue.game.state.character.mob.boss.BigDemonMobState
import ru.hse.sd.rogue.game.state.character.mob.regular.*

class ClassicDungeonMobsFabric : MobsFabricBase() {
    override val easyMobs = listOf(
        PositionToState { TinyZombieMobState(it.asMutable()) },
        PositionToState { GoblinMobState(it.asMutable()) },
        PositionToState { ImpMobState(it.asMutable()) },
    )

    override val mediumMobs = listOf(
        PositionToState { SkeletonMobState(it.asMutable()) }
    )
    override val hardMobs = listOf(
        PositionToState { NecromancerMobState(it.asMutable()) }
    )
    override val bossMob = PositionToState { BigDemonMobState(it.asMutable()) }

}