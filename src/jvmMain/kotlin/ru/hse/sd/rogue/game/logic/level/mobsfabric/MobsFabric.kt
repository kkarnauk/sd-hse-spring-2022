package ru.hse.sd.rogue.game.logic.level.mobsfabric

import ru.hse.sd.rogue.game.logic.position.Position
import ru.hse.sd.rogue.game.state.character.mob.MobState


/**
 * Generate mobs
 */
interface MobsFabric {
    /**
     * Generates some simple mob at [position]
     */
    fun generateRandomEasyMob(position: Position): MobState
    /**
     * Generates some medium mob at [position]
     */
    fun generateRandomMediumMob(position: Position): MobState
    /**
     * Generates some hard mob at [position]
     */
    fun generateRandomHardMob(position: Position): MobState
    /**
     * Generates some boss mob at [position]
     */
    fun generateBossMob(position: Position): MobState
}