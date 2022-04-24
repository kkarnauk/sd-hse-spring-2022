package ru.hse.sd.rogue.game.state.character

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.common.Effect
import ru.hse.sd.rogue.game.logic.position.MutablePosition

/**
 * General state of a mob.
 */
sealed class MobState(
    health: Health,
    position: MutablePosition,
    meleeDamage: Damage
) : CharacterState(health, position, meleeDamage), Cloneable {
    override val damage: Damage
        get() = meleeDamage

    override val effects: List<Effect> = emptyList()

    public override fun clone(): MobState {
        throw CloneNotSupportedException()
    }
}
