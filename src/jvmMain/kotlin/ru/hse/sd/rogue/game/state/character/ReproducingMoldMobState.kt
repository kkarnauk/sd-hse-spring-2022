package ru.hse.sd.rogue.game.state.character

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.state.CloneableState

/**
 * A mold that periodically reproduces itself in random free cell nearby
 */
class ReproducingMoldMobState(
    position: MutablePosition
) : MobState(Health(3), position, Damage(0, 1)), CloneableState<ReproducingMoldMobState> {
    override fun clone(): ReproducingMoldMobState {
        return ReproducingMoldMobState(MutablePosition(position.x, position.y))
    }
}
