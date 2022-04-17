package ru.hse.sd.rogue.game.state.character.mob.regular

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.state.character.mob.MobState
import ru.hse.sd.rogue.game.state.character.mob.boss.BigDemonMobState

/**
 * State of a necromancer.
 */
class NecromancerMobState(position: MutablePosition) : MobState(Health(2), position, Damage(1, 2)) {
    // TODO
    override fun clone(): NecromancerMobState {
        return NecromancerMobState(MutablePosition(position.x, position.y))
    }
}
