package ru.hse.sd.rogue.game.state.character

import ru.hse.sd.rogue.game.logic.characteristics.MutableDamage
import ru.hse.sd.rogue.game.logic.characteristics.MutableHealth
import ru.hse.sd.rogue.game.logic.position.MutablePosition

/**
 * State of a mushroom.
 */
class MushroomMobState(
    position: MutablePosition
) : MobState(MutableHealth(1), position, MutableDamage(0, 1))
