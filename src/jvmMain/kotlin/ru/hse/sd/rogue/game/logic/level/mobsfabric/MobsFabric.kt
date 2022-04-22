package ru.hse.sd.rogue.game.logic.level.mobsfabric

import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.character.mob.MobState

interface MobsFabric {
    fun generateRandomEasyMob(position: Position): MobState
    fun generateRandomMediumMob(position: Position): MobState
    fun generateRandomHardMob(position: Position): MobState
    fun generateBossMob(position: Position): MobState
}