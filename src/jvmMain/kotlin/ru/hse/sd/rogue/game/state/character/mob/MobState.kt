package ru.hse.sd.rogue.game.state.character.mob

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.common.Effect
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.state.character.CharacterState

/**
 * General state of a mob.
 */
open class MobState(
    health: Health,
    position: MutablePosition,
    meleeDamage: Damage
) : CharacterState(health, position, meleeDamage) {
    override val damage: Damage
        get() = meleeDamage
    override val effects: List<Effect> = emptyList()
}
