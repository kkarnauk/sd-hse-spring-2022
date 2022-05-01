package ru.hse.sd.rogue.game.state.character

import ru.hse.sd.rogue.game.logic.characteristics.Damage
import ru.hse.sd.rogue.game.logic.characteristics.Health
import ru.hse.sd.rogue.game.logic.position.MutablePosition

/**
 * State of angry pig.
 */
class AngryPigMobState(
    position: MutablePosition
) : MobState(Health(5), position, Damage(1, 2))
