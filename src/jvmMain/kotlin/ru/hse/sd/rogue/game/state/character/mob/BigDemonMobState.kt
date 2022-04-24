package ru.hse.sd.rogue.game.state.character.mob

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.position.MutablePosition

/**
 * State of the boss in the game.
 */
class BigDemonMobState(position: MutablePosition) : MobState(Health(100), position, Damage(3, 5)) {
    // TODO
    override fun clone(): BigDemonMobState {
        return BigDemonMobState(MutablePosition(position.x, position.y))
    }
}
