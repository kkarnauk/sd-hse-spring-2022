package ru.hse.sd.rogue.game.state.character.mob.regular

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.state.character.mob.MobState

/**
 * State of an impl.
 */
class ImpMobState(position: MutablePosition) : MobState(Health(1), position, Damage(0, 1)) {
    // TODO
}
