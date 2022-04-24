package ru.hse.sd.rogue.game.state.character

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.position.MutablePosition

/**
 * State of the rino boss.
 */
class RinoMobState(position: MutablePosition) : MobState(Health(100), position, Damage(3, 5)) {
    // TODO
}
