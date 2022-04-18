package ru.hse.sd.rogue.game.logic.item

import ru.hse.sd.rogue.game.logic.common.Effect

/**
 * Potion that can be used on a characters in order to increase or decrease its characteristics.
 */
class Potion(
    /**
     * Effect that will be applied
     */
    val effect: Effect
) : Item() {
    // TODO
    override val usable: Boolean
        get() = TODO("Not yet implemented")
}
