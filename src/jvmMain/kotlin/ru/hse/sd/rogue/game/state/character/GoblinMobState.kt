package ru.hse.sd.rogue.game.state.character

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.position.MutablePosition

/**
 * State of a goblin.
 */
class GoblinMobState(position: MutablePosition) : MobState(Health(1), position, Damage(0, 1)) {
    // TODO
    override fun clone(): GoblinMobState {
        return GoblinMobState(MutablePosition(position.x, position.y))
    }
}
