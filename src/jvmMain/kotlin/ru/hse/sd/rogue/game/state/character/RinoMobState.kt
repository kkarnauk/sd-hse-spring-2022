package ru.hse.sd.rogue.game.state.character

import ru.hse.sd.rogue.game.logic.characteristics.MutableDamage
import ru.hse.sd.rogue.game.logic.characteristics.MutableHealth
import ru.hse.sd.rogue.game.logic.position.MutablePosition

/**
 * State of the rino boss.
 */
class RinoMobState(
    position: MutablePosition
) : MobState(MutableHealth(100), position, MutableDamage(3, 5))
