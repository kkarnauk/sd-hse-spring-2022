package ru.hse.sd.rogue.game.state.character

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.position.MutablePosition

class ReproducingMoldMobState(position: MutablePosition) : MobState(Health(3), position, Damage(0, 1)) {
    override fun clone(): ReproducingMoldMobState {
        return ReproducingMoldMobState(MutablePosition(position.x, position.y))
    }
}