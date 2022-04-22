package ru.hse.sd.rogue.game.logic.level.mobsfabric

import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.character.mob.MobState

/**
 * Standard way to implement [MobsFabric]
 */
abstract class MobsFabricBase : MobsFabric {
    protected fun interface PositionToState {
        fun generateState(position: Position): MobState
    }

    protected abstract val easyMobs: List<PositionToState>
    protected abstract val mediumMobs: List<PositionToState>
    protected abstract val hardMobs: List<PositionToState>
    protected abstract val bossMob: PositionToState

    override fun generateRandomEasyMob(position: Position): MobState = easyMobs.random().generateState(position)

    override fun generateRandomMediumMob(position: Position): MobState = mediumMobs.random().generateState(position)

    override fun generateRandomHardMob(position: Position): MobState = hardMobs.random().generateState(position)

    override fun generateBossMob(position: Position): MobState = bossMob.generateState(position)
}