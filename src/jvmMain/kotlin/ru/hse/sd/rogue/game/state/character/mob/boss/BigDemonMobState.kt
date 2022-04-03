package ru.hse.sd.rogue.game.state.character.mob.boss

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.position.MutablePosition
import ru.hse.sd.rogue.game.state.character.mob.MobState

class BigDemonMobState(position: MutablePosition) : MobState(Health(100), position, Damage(3, 5)) {
    // TODO
}
