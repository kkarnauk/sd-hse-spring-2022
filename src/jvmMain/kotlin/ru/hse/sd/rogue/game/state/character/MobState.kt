package ru.hse.sd.rogue.game.state.character

import ru.hse.sd.rogue.game.logic.characteristics.MutableDamage
import ru.hse.sd.rogue.game.logic.characteristics.MutableHealth
import ru.hse.sd.rogue.game.logic.common.Effect
import ru.hse.sd.rogue.game.logic.position.MutablePosition

/**
 * General state of a mob.
 */
sealed class MobState(
    health: MutableHealth,
    position: MutablePosition,
    meleeDamage: MutableDamage
) : CharacterMutableState(health, position, meleeDamage) {
    override val damage: MutableDamage
        get() = meleeDamage

    override val effects: List<Effect> = emptyList()
}
