package ru.hse.sd.rogue.game.state.character

import ru.hse.sd.rogue.game.logic.characteristics.MutableDamage
import ru.hse.sd.rogue.game.logic.characteristics.MutableHealth
import ru.hse.sd.rogue.game.logic.position.MutablePosition

/**
 * State of chameleon.
 */
class ChameleonMobState(
    position: MutablePosition
) : MobState(MutableHealth(5), position, MutableDamage(1, 2))

