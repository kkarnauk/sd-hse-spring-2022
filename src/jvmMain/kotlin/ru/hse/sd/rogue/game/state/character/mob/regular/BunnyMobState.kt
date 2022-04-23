package ru.hse.sd.rogue.game.state.character.mob.regular

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.state.character.mob.MobState

/**
 * State of a bunny.
 */
class BunnyMobState(position: MutablePosition) : MobState(Health(2), position, Damage(1, 2)) {
    // TODO
}
