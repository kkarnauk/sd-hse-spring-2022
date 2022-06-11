package ru.hse.sd.rogue.game.state.character

import ru.hse.sd.rogue.game.logic.characteristics.MutableSpeed
import ru.hse.sd.rogue.game.state.MutableState

/**
 * State of movement for a character.
 */
class MovementMutableState(
    /**
     * [MutableSpeed] for a character.
     */
    val speed: MutableSpeed
) : MutableState {
    /**
     * Whether a character can move.
     */
    var canMove: Boolean = true
}
