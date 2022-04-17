package ru.hse.sd.rogue.game.state.character.mob.regular

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.state.character.mob.MobState

class ReproductingMoldMobState(position: MutablePosition) : MobState(Health(3), position, Damage(0, 1)) {
    override fun clone(): ReproductingMoldMobState {
        return ReproductingMoldMobState(MutablePosition(position.x, position.y))
    }
}