package ru.hse.sd.rogue.game.state.character

import ru.hse.sd.rogue.game.logic.characteristics.MutableDamage
import ru.hse.sd.rogue.game.logic.characteristics.MutableHealth
import ru.hse.sd.rogue.game.logic.position.MutablePosition

/**
 * State of a necromancer.
 */
class NecromancerMobState(
    position: MutablePosition
) : MobState(MutableHealth(2), position, MutableDamage(1, 2))
