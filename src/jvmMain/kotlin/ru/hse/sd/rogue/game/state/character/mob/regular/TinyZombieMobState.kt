package ru.hse.sd.rogue.game.state.character.mob.regular

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.state.character.mob.MobState

/**
 * State of tiny zombie.
 */
class TinyZombieMobState(position: MutablePosition) : MobState(Health(5), position, Damage(0, 1)) {
    // TODO
}
